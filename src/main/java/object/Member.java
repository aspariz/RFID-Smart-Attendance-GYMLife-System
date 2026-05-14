/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

/**
 *
 * @author ADVAN
 */
public class Member {
    private String uidrfid;
    private String namamember;
    private String idmember;
    private String paket;
    
    public Member(){
    }
    
    public Member(String uidrfid, String namamember, String idmember, String paket) {
        this.uidrfid = uidrfid;
        this.namamember = namamember;
        this.idmember = idmember;
        this.paket = paket;
    }
    
    
    public String tostString(){
        return "Member{"
                + "uidrfid" + uidrfid
                + ", namamember" + namamember
                + ",idmember" + idmember
                + ", paket" + paket
                + '}';
    }
    public String getuidrfid(){
        return uidrfid;
    }
    
    public void setuidrfid(String uidrfid){
        this.uidrfid = uidrfid;
    }
    
    public  String getnamamember(){
        return namamember;
    }
    
    public void setnamamember(String namamember){
        this.namamember = namamember;
    }
    
    public String getidmember(){
        return idmember;
    
    }
    
    public void setidmember(String idmember){
       this.idmember = idmember;
    }
    
    public String getpaket(){
        return paket;
    }
    
    public void setpaket(String paket){
        this.paket = paket;
    }

    public void setIdmember(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setUidRfid(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
