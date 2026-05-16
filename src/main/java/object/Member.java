package object;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Member {
    @BsonProperty("uidrfid")
    private String uidrfid;
    
    @BsonProperty("namamember")
    private String namamember;
    
    @BsonProperty("idmember")
    private String idmember;
    
    @BsonProperty("paket")
    private String paket;

    public Member() {}

    public Member(String uidrfid, String namamember, String idmember, String paket) {
        this.uidrfid = uidrfid;
        this.namamember = namamember;
        this.idmember = idmember;
        this.paket = paket;
    }

    public String getUidrfid() { return uidrfid; }
    public void setUidrfid(String uidrfid) { this.uidrfid = uidrfid; }

    public String getNamamember() { return namamember; }
    public void setNamamember(String namamember) { this.namamember = namamember; }

    public String getIdmember() { return idmember; }
    public void setIdmember(String idmember) { this.idmember = idmember; }

    public String getPaket() { return paket; }
    public void setPaket(String paket) { this.paket = paket; }

    @Override
    public String toString() {
        return "Member{uidrfid=" + uidrfid + ", namamember=" + namamember + 
               ", idmember=" + idmember + ", paket=" + paket + '}';
    }
}