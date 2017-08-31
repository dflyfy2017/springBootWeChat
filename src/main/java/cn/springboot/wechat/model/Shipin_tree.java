package cn.springboot.wechat.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Component
@Entity
public class Shipin_tree {
  private Integer id;
  private String pId;
  private String name;
  private String CODE;
  private String PhoneCODE;
  private Integer Important;
  private Integer PhoneImportant;
  private Float LGTD;
  private Float LATD;
  private Integer order;
  private String icon;
  private Integer tcount;
  private String isgray;
  private String phonemodel;
  private String phoneshowmodel;
  private String type;
  @Id
  private String _id;
  private String _pId;
  private String SoftType;

  public Shipin_tree() {
  }


  public String getpId() {
    return pId;
  }

  public void setpId(String pId) {
    this.pId = pId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCODE() {
    return CODE;
  }

  public void setCODE(String CODE) {
    this.CODE = CODE;
  }

  public String getPhoneCODE() {
    return PhoneCODE;
  }

  public void setPhoneCODE(String phoneCODE) {
    PhoneCODE = phoneCODE;
  }

  public Integer getImportant() {
    return Important;
  }

  public void setImportant(Integer important) {
    Important = important;
  }

  public Integer getPhoneImportant() {
    return PhoneImportant;
  }

  public void setPhoneImportant(Integer phoneImportant) {
    PhoneImportant = phoneImportant;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Integer getTcount() {
    return tcount;
  }

  public void setTcount(Integer tcount) {
    this.tcount = tcount;
  }

  public Float getLGTD() {
    return LGTD;
  }

  public void setLGTD(Float LGTD) {
    this.LGTD = LGTD;
  }

  public Float getLATD() {
    return LATD;
  }

  public void setLATD(Float LATD) {
    this.LATD = LATD;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIsgray() {
    return isgray;
  }

  public void setIsgray(String isgray) {
    this.isgray = isgray;
  }

  public String getPhonemodel() {
    return phonemodel;
  }

  public void setPhonemodel(String phonemodel) {
    this.phonemodel = phonemodel;
  }

  public String getPhoneshowmodel() {
    return phoneshowmodel;
  }

  public void setPhoneshowmodel(String phoneshowmodel) {
    this.phoneshowmodel = phoneshowmodel;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String get_pId() {
    return _pId;
  }

  public void set_pId(String _pId) {
    this._pId = _pId;
  }

  public String getSoftType() {
    return SoftType;
  }

  public void setSoftType(String softType) {
    SoftType = softType;
  }

  @Override
  public String toString() {
    return "Shipin_tree{" +
            "id=" + id +
            ", pId='" + pId + '\'' +
            ", name='" + name + '\'' +
            ", CODE='" + CODE + '\'' +
            ", phoneCODE='" + PhoneCODE + '\'' +
            ", Important=" + Important +
            ", PhoneImportant=" + PhoneImportant +
            ", LGTD=" + LGTD +
            ", LATD=" + LATD +
            ", order=" + order +
            ", icon='" + icon + '\'' +
            ", tcount=" + tcount +
            ", isgray='" + isgray + '\'' +
            ", phonemodel='" + phonemodel + '\'' +
            ", phoneshowmodel='" + phoneshowmodel + '\'' +
            ", type='" + type + '\'' +
            ", _id='" + _id + '\'' +
            ", _pId='" + _pId + '\'' +
            ", SoftType='" + SoftType + '\'' +
            '}';
  }
}