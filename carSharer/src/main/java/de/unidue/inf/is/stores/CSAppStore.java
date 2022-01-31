package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.*;
import de.unidue.inf.is.utils.DBUtil;
import java.io.Closeable;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class CSAppStore implements Closeable {

    private Connection connection;
    private boolean complete;

    public CSAppStore () throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public int getBenutzerId(int fahrtId) {
        int results=0;

        try {
            PreparedStatement ps =connection.prepareStatement("select b.bid as bid from dbp061.fahrt p, dbp061.benutzer b where p.anbieter = b.bid and p.fid= ?");
            ps.setInt(1, fahrtId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                results = rs.getInt("bid");
            }
            connection.commit();
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }
    
    public int getKundeId() {
        int results=0;

        try {
            PreparedStatement ps =connection.prepareStatement("select bid from dbp061.benutzer order by rand() fetch first 1 rows only");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                results = rs.getInt("bid");
            }
            connection.commit();
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }
    
    public int getBewertungId(int fahrtId, int benutzerId) {
        int results=0;

        try {
            PreparedStatement ps =connection.prepareStatement("select bewertung as beid from dbp061.schreiben where fahrt = ? and benutzer = ? ");
            ps.setInt(1, fahrtId);
            ps.setInt(2, benutzerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                results = rs.getInt("beid");
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }  


    public Benutzer getErstellerInfo() throws StoreException{
        Benutzer results = new Benutzer();

        try {
            PreparedStatement ps = connection.prepareStatement("select b.email as anbieter, b.name" +
                    "                     from dbp061.fahrt p, dbp061.benutzer b" +
                    "                    where p.anbieter = b.bid and" +
                    "                          b.name = 'DummyUser' ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String anbieter = rs.getString("anbieter");
                String name = rs.getString("name");
                results= new Benutzer(anbieter, name);
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;

    }

    public Benutzer getErstellerByFahrtId(int fahrtId) throws StoreException{
        Benutzer results = new Benutzer();

        try {
            PreparedStatement ps = connection.prepareStatement("select b.email as anbieter, b.name" +
                    "                     from dbp061.fahrt p, dbp061.benutzer b" +
                    "                    where p.anbieter = b.bid and" +
                    "                          p.fid = ?");

            ps.setInt(1, fahrtId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String anbieter = rs.getString("anbieter");
                String name = rs.getString("name");
                results= new Benutzer(anbieter, name);
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;

    }




    public ArrayList<Reservieren> getKunde(int fid) {
    	ArrayList<Reservieren> results = new ArrayList<>();
        

        try {
            PreparedStatement ps =connection.prepareStatement("select s.kunde, b.name as benutzername, s.fahrt, s.anzPlaetze" +
                    "   from dbp061.reservieren s, dbp061.benutzer b" +
                    "   where s.kunde = b.bid and" +
                    "      s.fahrt=?");
            ps.setInt(1, fid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	int kunde = rs.getInt("kunde");
                String benutzername = rs.getString("benutzername");
                int fahrtId = rs.getInt("fahrt");
                int anzPlaetze = rs.getInt("anzPlaetze");
                results.add(new Reservieren(kunde,fahrtId,anzPlaetze,benutzername));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }


    public ArrayList<Bewertung> getListRating(int fid) {
        ArrayList<Bewertung> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select b.email, t.textnachricht, t.rating" +
                    "   from dbp061.schreiben s, dbp061.benutzer b, dbp061.bewertung t" +
                    "   where s.benutzer = b.bid and s.bewertung = t.beid and " +
                    "      s.fahrt = ?");
            ps.setInt(1, fid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String textnachricht = rs.getString("textnachricht");
                int rating = rs.getInt("rating");
                results.add(new Bewertung(email, textnachricht, rating));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }
        return results;
    }


    public ArrayList<Fahrt> getOpenFahrt() {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {

            PreparedStatement ps = connection.prepareStatement("select * from (select distinct k.icon, p.fid, p.startort, p.zielort, p.fahrtkosten from dbp061.transportmittel k, dbp061.fahrt p, dbp061.benutzer b where k.tid = p.transportmittel and b.bid = p.anbieter and p.status = 'offen'group by k.icon,p.fid, p.startort, p.zielort, p.fahrtkosten) join ((select f.maxPlaetze - sum(r.anzPlaetze) as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f join dbp061.reservieren r on f.fid = r.fahrt group by f.maxPlaetze, r.fahrt,f.fid) union all (select f.maxPlaetze as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f where f.fid not in (select fahrt from dbp061.reservieren))) on fid=fahrtid ");
           // PreparedStatement ps2 = connection.prepareStatement("select f.maxPlaetze - sum(r.anzPlaetze) as freiePlaetze from dbp172.fahrt f join dbp172.reservieren r on f.fid = r.fahrt group by f.maxPlaetze, r.fahrt) union all (select f.maxPlaetze from dbp172.fahrt f where f.fid not in (select fahrt from dbp172.reservieren))");
            ResultSet rs = ps.executeQuery();
           // ResultSet rs2 = ps2.executeQuery();
            //rs2.next()
            while(rs.next()) {
                String icon = rs.getString("icon");
                System.out.println(icon);
                int fid = rs.getInt("fid");
                System.out.println(fid);
                String startort = rs.getString("startort");
                System.out.println("von : " + startort);
                String zielort = rs.getString("zielort");
                System.out.println("nach : " + zielort);
                double fahrtkosten = rs.getDouble("fahrtkosten");
                System.out.println("fahrtkosten : " + fahrtkosten);
                //rs2.next();
                int freiePlaetze = rs.getInt("freiePlaetze");
                System.out.println("frei:" + freiePlaetze);
                
                Fahrt f1 = new Fahrt(icon,fid, startort, zielort,fahrtkosten,freiePlaetze);
                f1.printFahrt(f1);
                
                results.add(new Fahrt(icon,fid, startort, zielort,fahrtkosten,freiePlaetze));
                
            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        } 
        return results;
    }

    public ArrayList<Fahrt> getClosedFahrt() {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(" select k.icon, p.fid, p.startort, p.zielort, p.status " +
                    "                                      from dbp061.transportmittel k,dbp061.fahrt p, dbp061.benutzer b " +
                    "                                      where k.tid = p.transportmittel and " +
                    "                                            b.bid = p.anbieter and " +
                    "                                            p.status = 'geschlossen' " +
                    "                                      group by k.icon, p.fid, p.startort, p.zielort, p.status ");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                int fid = rs.getInt("fid");
                String startort = rs.getString("startort");
                String zielort = rs.getString("zielort");
                String status = rs.getString("status");
                results.add(new Fahrt(icon,fid, startort, zielort, status));
            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        } 
        return results;
    }

    public int getNumberOfFahrten() {
        int results =0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(fid) as numberOfFahrten from dbp061.fahrt");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results = rs.getInt("numberOfFahrten");
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }
    



    public boolean addFahrt (Fahrt fahrt) throws StoreException {
        boolean result = false;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp061.fahrt(startort, zielort, fahrtdatumzeit , maxPlaetze, fahrtkosten, anbieter, transportmittel, beschreibung) values (?, ?, ?, ?, ?, ?, ?, ?)");

           // ps.setInt(1, fahrt.getId());
            ps.setString(1, fahrt.getStartort());
            ps.setString(2, fahrt.getZielort());
            
            String datum1 = fahrt.getFahrtdatumzeit();
            String datum3 = datum1 + ":00";
            System.out.println("it is : " + datum3);
            java.sql.Timestamp datum2 = java.sql.Timestamp.valueOf(datum3);
      /*      String pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSSS";
            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
            LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(datum1));
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            System.out.println(timestamp);  */
            
            ps.setTimestamp(3, datum2);
            ps.setInt(4, fahrt.getMaxPlaetze());
            ps.setDouble(5, fahrt.getFahrtkosten());
            ps.setInt(6, fahrt.getAnbieter());
            ps.setInt(7, fahrt.getTransportmittel());
            
            String bes = fahrt.getBeschreibung();
           // ps.setString(9, fahrt.getBeschreibung());
            Clob myClob = new javax.sql.rowset.serial.SerialClob(bes.toCharArray());
            //ps.setString(9, fahrt.getBeschreibung());
            ps.setClob(8, myClob);

            int count = ps.executeUpdate();
            result = (count > 0);
            connection.commit();
            
            //result = (count > 0);
            System.out.println(result);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }



    public ArrayList<Transportmittel> getTransportmitteln() {
        ArrayList<Transportmittel> result = new ArrayList<Transportmittel>();

        try {


            PreparedStatement ps = connection.prepareStatement("select * from dbp061.transportmittel");


            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int tid = rs.getInt("tid");
                    String name = rs.getString("name");
                    String icon = rs.getString("icon");


                    result.add(new Transportmittel(tid, name, icon));
                }

        }
        catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        } 

        return result;
    }




    public boolean updateFahrt (Fahrt fahrt) throws StoreException {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("update dbp061.fahrt set fid=?, startort=?, zielort=?, fahrtdatumzeit=?, maxPlaetze=?, " +
        		                                       "  fahrtkosten=?, anbieter=?, transportmittel=?,beschreibung=?");

        ps.setInt(1, fahrt.getId());
        ps.setString(2, fahrt.getStartort());
        ps.setString(3, fahrt.getZielort());
        ps.setString(4, fahrt.getFahrtdatumzeit());
        ps.setInt(5, fahrt.getMaxPlaetze());
        ps.setDouble(6, fahrt.getFahrtkosten());
        ps.setInt(7, fahrt.getAnbieter());
        ps.setInt(8, fahrt.getTransportmittel());
       // ps.setClob(9, fahrt.getBeschreibung());

            ps.executeUpdate();

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        } 
        return result;
    }


    public int getTransportmittel(String transportmittel) {
        int results =0;

        try {
            PreparedStatement ps =connection.prepareStatement("select tid from dbp061.Transportmittel where name=?");

            ps.setString(1, transportmittel);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("tid");
                results = id;
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }

    public int getNumberCreatedFahrt() {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(startort) as createdFahrt" +
                    "   from dbp061.fahrt" +
                    "   where anbieter=?");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int createdFahrt = rs.getInt("createdFahrt");
                results = createdFahrt;
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }

    public int getNumberCreatedFahrtByAnbieter(int anbieter) {
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(startort) as createdFahrt" +
                    "   from dbp061.fahrt" +
                    "   where anbieter= ? and fid = ?");
            ps.setInt(1, anbieter);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int createdFahrt = rs.getInt("createdFahrt");
                results = createdFahrt;
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }

    public int getNumberReservedFahrt() {                //not sure
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(fahrt) as supportedFahrt" +
                    "   from dbp061.reservieren ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int reservedFahrt = rs.getInt("reservedFahrt");
                results = reservedFahrt;
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }

    public int getNumberReservedFahrtByKunde(int kunde) {           //not sure
        int results= 0;

        try {
            PreparedStatement ps =connection.prepareStatement("select count(fahrt) as supportedFahrt" +
                    "   from dbp061.reservieren" +
                    "   where kunde= ? and fid = ?");
            ps.setInt(1, kunde);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int reservedFahrt = rs.getInt("reservedFahrt");
                results = reservedFahrt;
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }

    public ArrayList<Fahrt> getCreatedFahrt() {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.startort, p.zielort, p.status " +
                    "   from dbp061.transportmittel k, dbp061.fahrt p" +
                    "   where k.tid = p.transportmittel  " +
                    "   group by k.icon, p.startort, p.zielort, p.status");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                String startort = rs.getString("startort");
                String zielort =rs.getString("zielort");
                String status =rs.getString("status");
                results.add(new Fahrt(icon, startort, zielort, status));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }

    public ArrayList<Fahrt> getCreatedFahrtByAnbieter(int anbieter) {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.fid, p.startort, p.zielort, p.status " +
                    "   from dbp061.transportmittel k, dbp061.fahrt p" +
                    "     where   k.tid = p.transportmittel and" +
                    "      p.anbieter = ?" +
                    "   group by k.icon, p.fid, p.startort, p.status");
            ps.setInt(1, anbieter);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int fid = rs.getInt("fid");
                String startort =rs.getString("startort");
                String zielort =rs.getString("zielort");
                String status =rs.getString("status");
                results.add(new Fahrt(icon,fid, startort, zielort, status));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        }  
        return results;
    }

    public ArrayList<Fahrt> getReservedFahrt() {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.fid, p.startort, p.zielort, p.status" +
                    "   from dbp061.transportmittel k, dbp061.fahrt p" +
                    "   where p.transportmittel=k.tid  and p.status = 'geschlossen'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int fid = rs.getInt("fid");
                String startort =rs.getString("startort");
                String zielort =rs.getString("zielort");
                String status =rs.getString("status");
                results.add(new Fahrt(icon, fid, startort, zielort, status));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }

    public ArrayList<Fahrt> getReservedFahrtByKunde(int kunde) {
        ArrayList<Fahrt> results = new ArrayList<>();

        try {
            PreparedStatement ps =connection.prepareStatement("select k.icon, p.fid, p.startort, p.zielort, p.status" +
            		"   from dbp061.transportmittel k, dbp061.fahrt p" +
            		"   where p.transportmittel=k.tid ");
            ps.setInt(1, kunde);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String icon = rs.getString("icon");
                int fid = rs.getInt("fid");
                String startort =rs.getString("startort");
                String zielort =rs.getString("zielort");
                String status =rs.getString("status");
                results.add(new Fahrt(icon, fid, startort, zielort, status));
            }
        }catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new  StoreException(e);
        } 
        return results;
    }

    public Fahrt getFahrtInfo(int fahrtid) {
        Fahrt results = null;

        try {

            
        	PreparedStatement ps = connection.prepareStatement("select * from dbp061.fahrt , dbp061.benutzer, dbp061.transportmittel, ((select f.maxPlaetze - sum(r.anzPlaetze) as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f join dbp061.reservieren r on f.fid = r.fahrt group by f.maxPlaetze, r.fahrt,f.fid) union all (select f.maxPlaetze as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f where f.fid not in (select fahrt from dbp061.reservieren))) where fid = ? and fid = fahrtid and anbieter = bid and tid = transportmittel");
            //PreparedStatement ps2 = connection.prepareStatement("select f.maxPlaetze - sum(r.anzPlaetze) as freiePlaetze from dbp172.fahrt f join dbp172.reservieren r on f.fid = r.fahrt group by f.maxPlaetze, r.fahrt");
            ps.setInt(1, fahrtid);
           // ps2.setInt(2, fahrtid);
            ResultSet rs = ps.executeQuery();
           //ResultSet rs2 = ps2.executeQuery();
          //&& rs2.next()
            
            while(rs.next()) {
                String icon = rs.getString("icon");
                String startort = rs.getString("startort");
                String zielort = rs.getString("zielort");
                String fahrtdatumzeit = rs.getString("fahrtdatumzeit");
                int freiePlaetze = rs.getInt("freiePlaetze");
                double fahrtkosten = rs.getDouble("fahrtkosten");
                String email = rs.getString("email");
                int transportmittel = rs.getInt("transportmittel");
                String status = rs.getString("status");
                String beschreibung = rs.getString("beschreibung");
                if(beschreibung== null) {
                	beschreibung = " - ";
                }  
                
                //System.out.println(beschreibung);      
                //System.out.println(fahrtdatumzeit);
                
              //  String fahrtdatumzeit = fahrtZeit.toString();
              //  System.out.println(fahrtZeit);
            //    java.sql.Clob beschreibung = rs.getClob("beschreibung");
           //     int freiePlaetze = rs2.getInt("freiePlaetze");
                
                results = new Fahrt(icon, startort, zielort, fahrtdatumzeit, freiePlaetze,fahrtkosten,email,transportmittel,status,beschreibung);

            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        }  
        return results;
    }


    public Fahrt getFahrtEdit(int fahrtId) {
        Fahrt results = null;

        try {

            PreparedStatement ps = connection.prepareStatement("select * from dbp061.fahrt where id= ?");
            ps.setInt(1, fahrtId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String startort = rs.getString("startort");
                String zielort = rs.getString("zielort");
                java.sql.Timestamp datum = rs.getTimestamp("datum");
                int maxPlaetze = rs.getInt("maxPlaetze");
                double fahrtkosten = rs.getDouble("fahrtkosten");
                String status = rs.getString("status");
                java.sql.Clob beschreibung = rs.getClob("beschreibung");
                int anbieter = rs.getInt("anbieter");
                int transportmittel = rs.getInt("transportmittel");

             //   results = new Fahrt(id,startort,zielort,datum,maxPlaetze,fahrtkosten,anbieter,transportmittel,beschreibung);
            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        }  
        return results;
    }

    public int getNumberOfBewertung () {
        int results = 0;

        try {

            PreparedStatement ps = connection.prepareStatement("select count(beid) as beid from dbp061.bewertung ");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                results = rs.getInt("beid");
            }
            connection.commit();
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        }  
        return results;
    }

  /*  public boolean updateBewertung(Bewertung bewertung) {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp172.bewertung(beid, textnachricht, erstellungsdatum, rating) values (?, ?, ?, ?)");

        ps.setInt(1, bewertung.getBeid());
        
        String bes = bewertung.getTextnachricht();
        Clob myClob = new javax.sql.rowset.serial.SerialClob(bes.toCharArray());
        ps.setClob(2, myClob);
        
        String bewertung1 = bewertung.getErstellungsdatum();
        String bewertung3 = bewertung1 + ":00";
        java.sql.Timestamp bewertung2 = java.sql.Timestamp.valueOf(bewertung3);
        ps.setTimestamp(3, bewertung2);
        
        ps.setInt(4, bewertung.getRating());


            ps.executeUpdate();

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
            throw  new StoreException(e);
        }
        return result;
    }   */

    public boolean updateSchreiben(Schreiben schreiben) {
        boolean result;
        try{PreparedStatement ps = connection.prepareStatement("insert into dbp061.schreiben(benutzer, fahrt, bewertung) values (?, ?, ?)");

            int benutzer = schreiben.getBenutzer();
            System.out.println("here is bid: " + benutzer);
            ps.setInt(1, benutzer);
            
            int fahrt = schreiben.getFahrt();
            System.out.println("here is fid: " + fahrt);
            ps.setInt(2, fahrt);
            
            int bewertung = schreiben.getBewertung();
            System.out.println("here is beid: " + bewertung);
            ps.setInt(3, bewertung);

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        } 
        return result;
    }

    public int getFreiePlaetzeFahrt (int fid) {
        int results = 0;

        try {

            PreparedStatement ps = connection.prepareStatement("select freiePlaetze from ((select f.maxPlaetze - sum(r.anzPlaetze) as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f join dbp061.reservieren r on f.fid = r.fahrt group by f.maxPlaetze, r.fahrt,f.fid) union all (select f.maxPlaetze as freiePlaetze, f.fid as fahrtid from dbp061.fahrt f where f.fid not in (select fahrt from dbp061.reservieren))) where fahrtid = ?");
            ps.setInt(1, fid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	
                results = rs.getInt("freiePlaetze");
                System.out.println("FreiePlaetze: " + results);
            }
            connection.commit();
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        }  
        return results;
    }   

    public String checkKunde(String user) {
        String result = "";

        try{PreparedStatement ps = connection.prepareStatement("select kunde from dbp061.reservieren  where kunde = ?");

            ps.setString(1, user);

            ResultSet rs =ps.executeQuery();

            while(rs.next()) {

                result = rs.getString("kunde");}


        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }


    public boolean makeReservation(int kundeId, int fid, int anzPlaetze) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("insert into dbp061.reservieren(kunde, fahrt, anzPlaetze) values (?, ?, ?)");

            ps.setInt(1, kundeId);
            ps.setInt(2, fid);
            ps.setInt(3, anzPlaetze);

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        } 
        return result;
    }



    public boolean deleteBewertung(int fahrtId) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp061.bewertung where beid=?");

            ps.setInt(1, fahrtId);

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }

    public boolean deleteReservieren(int fahrtId, int benutzerId) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp061.reservieren where fahrt=? and kunde = ?");

            ps.setInt(1, fahrtId);
            ps.setInt(2, benutzerId);

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        } 
        return result;
    }

    public boolean deleteFahrt(int fahrtId) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("delete from dbp061.fahrt where fid=?");

            ps.setInt(1, fahrtId);

            ps.executeUpdate();

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }

    public ArrayList<Reservieren> getKunden(int fahrtId) {
        ArrayList<Reservieren> result = new ArrayList<>();

        try{PreparedStatement ps = connection.prepareStatement("select kunde" +
                "   from dbp061.reservieren" +
                "   where fahrt = ?");

            ps.setInt(1, fahrtId);

            ResultSet rs =ps.executeQuery();

            while(rs.next()) {

                int kunde =rs.getInt("kunde");

                result.add(new Reservieren(kunde)); }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }


    public ArrayList<Fahrt> getFahrten(String  start, String ziel, String fahrtdatum) {
       ArrayList<Fahrt> results = new ArrayList<>();

      String start1 = "%" + start + "%";
      String ziel1 = "%" + ziel + "%";
      String fahrtdatum1 = "%" + fahrtdatum + "%";

        try {

            PreparedStatement ps = connection.prepareStatement("select f.icon, p.startort, p.zielort , p.fahrtkosten from dbp061.fahrt p, dbp061.transportmittel f where startort like ? and zielort like ? and fahrtdatumzeit like ? and p.transportmittel = f.tid ");
            ps.setString(1, start1);
            ps.setString(2, ziel1);
            ps.setString(3, fahrtdatum1);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String icon = rs.getString("icon");
                String startort = rs.getString("startort");
                String zielort = rs.getString("zielort");
                double fahrtkosten = rs.getDouble("fahrtkosten");
                
            //    System.out.println("it is: " + icon + " , " + startort+ " , " + zielort+ " , " + fahrtkosten);
                
                results.add(new Fahrt(icon, startort, zielort,fahrtkosten));
            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
        }  
        return results;
    }

    
    public boolean makeBewertung(Bewertung bewertung) {
        boolean result;

        try{PreparedStatement ps = connection.prepareStatement("insert into dbp061.bewertung(textnachricht, erstellungsdatum, rating) values (?, ?, ?)");

         // ps.setInt(1, bewertung.getBeid());
        
          String bes = bewertung.getTextnachricht();
          Clob myClob = new javax.sql.rowset.serial.SerialClob(bes.toCharArray());
          ps.setClob(1, myClob);
        
          String bewertung1 = bewertung.getErstellungsdatum();
       //   String bewertung3 = bewertung1 + ":00";
          java.sql.Timestamp bewertung2 = java.sql.Timestamp.valueOf(bewertung1);
          ps.setTimestamp(2, bewertung2);
        
          ps.setInt(3, bewertung.getRating());
            ps.executeUpdate();

            int count = ps.executeUpdate();
            connection.commit();
            
            result = (count > 0);

        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw  new StoreException(e);
        }  
        return result;
    }
    
    public double getAverageRating (int fahrtid) {
        double results = 0;

        try {

            PreparedStatement ps = connection.prepareStatement("select cast(round(sum(b.rating)*1.00 /count(b.rating), 2) as double) as average from dbp061.bewertung b, dbp061.schreiben s, dbp061.fahrt f where s.bewertung=b.beid and f.fid = s.fahrt and f.fid = ?");
            ps.setInt(1, fahrtid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                results = rs.getDouble("average");
            }
        } catch (SQLException e) {
        	try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
            throw new StoreException(e);
            
        } 
        return results;
    }
    
    
    public void complete() {
        complete = true;
    }


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
