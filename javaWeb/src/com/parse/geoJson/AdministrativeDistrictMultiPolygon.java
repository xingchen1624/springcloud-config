package com.parse.geoJson;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;


public class AdministrativeDistrictMultiPolygon {
	
	private    String   id; // ID
	@Size(max=200,message="网格名称")
	private    String   rangeName; // 网格名称
	@Size(max=64,message="上级机构代码")
	private    String   upCode; // 上级机构代码
	@Size(max=20,message="网格类型")
	private    String   type; // 网格类型
	@Size(max=10,message="数据权限范围 0全行 1本机构 2 下级机构")
	private    String   powerType; // 数据权限范围 0全行 1本机构 2 下级机构
	@Size(max=10,message="业务分类")
	private    String   businessType; // 业务分类
	@Size(max=100,message="业务系统")
	private    String   sysId; // 业务系统

	private    String   location; // 范围
	// 网格类型
	private    String   rangeType;
	@Size(max=100,message="创建人")
	private    String   userId; // 创建人
	@Size(max=64,message="创建时间")
	private    String   createTime; // 创建时间
	@Size(max=20,message="图形")
	private    String   shape; // 图形

	private    String   gdLocation; // 高德范围

	private    String   circle; // 圆形

	private    String   gdCircle; // 高德圆形
	
	private    String   jsonData; // 自定义数据

	private    String   tag; // 标签

	private    String   remark; // 说明

	private    String   provance; // 省

	private    String   city; // 市

	private    String   distrct; // 区
	@Size(max=64,message="网点代码")
	private    String   bankId; // 网点代码
	@Size(max=64,message="联系电话")
	private    String   telphone; // 联系电话
	@Size(max=64,message="管理员")
	private    String   manager; // 管理员
	@Size(max=200,message="网格员")
	private    String   personnel; // 网格员
	@Size(max=2,message="状态")
	private    String   status; // 状态
	
	private    String   isBusiness; // 
	
	private    String   custManagerid; // 
	
	private    String   productperson; // 
	
	private    String   productcompany; // 
	
	private    String   colorInBox; // 边框颜色
	
	private    String   borderColor; // 框内颜色
	@Size(max=2,message="行政级别是区还是街道,居委")
	private    String   rangeLevel; // 行政级别是区还是街道,居委

	private String street;

	private  String village;

	private String description;


	private    String   orgId; // 团队机构id（行政网格分配给团队时）

	private    String   relationId; // 关系表中的主键id

	private    String   districtId; // 小区网格id（ tt_range_district）主键

	private    String   groupCode; //团队

	private    String orgCode;//机构号

	private String  gridType;

	private String  community;

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getGridType() {
		return gridType;
	}

	public void setGridType(String gridType) {
		this.gridType = gridType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	private List<AdministrativeDistrictMultiPolygon> childList  =new ArrayList();//行政区划树

	public List<AdministrativeDistrictMultiPolygon> getChildList() {
		return childList;
	}

	public void setChildList(List<AdministrativeDistrictMultiPolygon> childList) {
		this.childList = childList;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
    
	public String getRangeName() {
		return rangeName;
	}
	
	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}
	
    
	public String getUpCode() {
		return upCode;
	}
	
	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}
	
    
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
    
	public String getPowerType() {
		return powerType;
	}
	
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	
    
	public String getBusinessType() {
		return businessType;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
    
	public String getSysId() {
		return sysId;
	}
	
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	
    
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
    
	public String getRangeType() {
		return rangeType;
	}
	
	public void setRangeType(String describe) {
		this.rangeType = rangeType;
	}
	
    
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
    
	public String getShape() {
		return shape;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
    
	public String getGdLocation() {
		return gdLocation;
	}
	
	public void setGdLocation(String gdLocation) {
		this.gdLocation = gdLocation;
	}
	
    
	public String getCircle() {
		return circle;
	}
	
	public void setCircle(String circle) {
		this.circle = circle;
	}
	
    
	public String getGdCircle() {
		return gdCircle;
	}
	
	public void setGdCircle(String gdCircle) {
		this.gdCircle = gdCircle;
	}
	
    
	public String getJsonData() {
		return jsonData;
	}
	
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
    
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
    
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
	public String getProvance() {
		return provance;
	}
	
	public void setProvance(String provance) {
		this.provance = provance;
	}
	
    
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
    
	public String getDistrct() {
		return distrct;
	}
	
	public void setDistrct(String distrct) {
		this.distrct = distrct;
	}
	
    
	public String getBankId() {
		return bankId;
	}
	
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
    
	public String getTelphone() {
		return telphone;
	}
	
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
    
	public String getManager() {
		return manager;
	}
	
	public void setManager(String manager) {
		this.manager = manager;
	}
	
    
	public String getPersonnel() {
		return personnel;
	}
	
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	public String getIsBusiness() {
		return isBusiness;
	}
	
	public void setIsBusiness(String isBusiness) {
		this.isBusiness = isBusiness;
	}
	
    
	public String getCustManagerid() {
		return custManagerid;
	}
	
	public void setCustManagerid(String custManagerid) {
		this.custManagerid = custManagerid;
	}
	
    
	public String getProductperson() {
		return productperson;
	}
	
	public void setProductperson(String productperson) {
		this.productperson = productperson;
	}
	
    
	public String getProductcompany() {
		return productcompany;
	}
	
	public void setProductcompany(String productcompany) {
		this.productcompany = productcompany;
	}
	
    
	public String getColorInBox() {
		return colorInBox;
	}
	
	public void setColorInBox(String colorInBox) {
		this.colorInBox = colorInBox;
	}
	
    
	public String getBorderColor() {
		return borderColor;
	}
	
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	
    
	public String getRangeLevel() {
		return rangeLevel;
	}
	
	public void setRangeLevel(String rangeLevel) {
		this.rangeLevel = rangeLevel;
	}
	

}
