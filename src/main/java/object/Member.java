package object;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Member {
    @BsonProperty("uidrfid")
    private String uidrfid;
    
    @BsonProperty("idmember")
    private String idmember;
    
    @BsonProperty("namamember")
    private String namamember;
    
    
    @BsonProperty("paket")
    private String paket;

    public Member() {}

    public Member(String uidrfid, String idmember, String namamember, String paket) {
        this.uidrfid = uidrfid;
        this.idmember = idmember;
        this.namamember = namamember;
        this.paket = paket;
    }

    public String getUidrfid() { return uidrfid; }
    public void setUidrfid(String uidrfid) { this.uidrfid = uidrfid; }
    
        public String getIdmember() { return idmember; }
    public void setIdmember(String idmember) { this.idmember = idmember; }

    public String getNamamember() { return namamember; }
    public void setNamamember(String namamember) { this.namamember = namamember; }



    public String getPaket() { return paket; }
    public void setPaket(String paket) { this.paket = paket; }

    @Override
    public String toString() {
        return "Member uidrfid=" + uidrfid + ", idmember=" + idmember + 
               ", namamember=" + namamember + ", paket=" + paket + '}';
    }
}