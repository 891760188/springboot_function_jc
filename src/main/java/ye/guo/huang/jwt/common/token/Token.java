package ye.guo.huang.jwt.common.token;

import java.util.Date;

//import com.yuchentech.data.entity.McrmToken;
//import com.yuchentech.data.pojo.UserInfo;

/**
 *  token信息类
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */
public class Token {

    private String tokenId;

    private Date createTime;

    private String userId;

    private String userName;

    private Long deadline;

    private String security;

    private String roleId;
    
    private String orgLevel;
    
    private String roleName;
    
    private String orgId;
    
    private String orgName;


    public Token() {
        this.createTime = new Date();
    }

    public Token(Date createTime) {
        this.createTime = createTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public McrmToken converTokenEntity(Token token, String jwt) {
//        McrmToken entity = new McrmToken();
//        entity.setTokenId(token.getTokenId());
//        entity.setJwt(jwt);
//        entity.setSecurity(token.getSecurity());
//        entity.setUserId(token.getUserId());
//        entity.setCreateTime(token.getCreateTime());
//        entity.setRoleId(token.getRoleId());
//        entity.setUserName(token.getUserName());
//        return entity;
//    }

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
