package com.jawa.furniture.cookie;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class RegUser implements java.io.Serializable {

	/**
     * 
     */
    private long id;
	/**
     * <p>
     *  用户名（可以是中文）
     * </p>
     */
    private java.lang.String username;
	/**
     * <p>
     *  密码
     * </p>
     */
    private java.lang.String password;
    /**
     * 用户密码安全
     */
    private Integer  passwordsafestate;
	

	/**
     * <p>
     *  注册时间
     * </p>
     */
    private java.util.Date registerTime;
	/**
     * <p>
     *  注册IP
     * </p>
     */
    private java.lang.String registerIp;
	/**
     * <p>
     *  状态：normal-正常 freezed-冻结 expired-失效
     * </p>
     */
    private java.lang.String state;
	/**
     * 
     */
    private java.lang.String email;
	/**
     * <p>
     *  类型：0个人会员，1商家会员
     * </p>
     */
    private int type;
    
    private String randNum;
    
	private long iscommend;
	
	
	private Date beforeLoginTime;
	
	

	public Integer getPasswordsafestate() {
		return passwordsafestate;
	}

	public void setPasswordsafestate(Integer passwordsafestate) {
		this.passwordsafestate = passwordsafestate;
	}

	public Date getBeforeLoginTime() {
		return beforeLoginTime;
	}

	public void setBeforeLoginTime(Date beforeLoginTime) {
		this.beforeLoginTime = beforeLoginTime;
	}

	//用户身份信息
	private Set identifyCard;
	
	//是否被激活记录
	private int isActive;
	
	//邮箱激活时间
	private Date activateEmailTime;
	
	
	public Date getActivateEmailTime() {
		return activateEmailTime;
	}

	public void setActivateEmailTime(Date activateEmailTime) {
		this.activateEmailTime = activateEmailTime;
	}


	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Set getIdentifyCard() {
		return identifyCard;
	}

	public void setIdentifyCard(Set identifyCard) {
		this.identifyCard = identifyCard;
	}

	public long getIscommend() {
		return iscommend;
	}

	public void setIscommend(long iscommend) {
		this.iscommend = iscommend;
	}

	public String getRandNum() {
		return randNum;
	}

	public void setRandNum(String randNum) {
		this.randNum = randNum;
	}

	public RegUser() {
    }
	
	public RegUser(long id,String username,String email,String state,long isCommend,Date registerTime,Date lastLoginTime) {
		this.id=id;
		this.username=username;
		this.email=email;
		this.state=state;
		this.iscommend=isCommend;
		this.registerTime=registerTime;
    }
    
    public RegUser(long id) {
    	this.id = id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }

    public long getId() {
    	return this.id;
    }
    public void setUsername(java.lang.String username) {
    	this.username = username;
    }

    public java.lang.String getUsername() {
    	return this.username;
    }
    public void setPassword(java.lang.String password) {
    	this.password = password;
    }

    public java.lang.String getPassword() {
    	return this.password;
    }
    public void setRegisterTime(java.util.Date registerTime) {
    	this.registerTime = registerTime;
    }

    public java.util.Date getRegisterTime() {
    	return this.registerTime;
    }
    public void setRegisterIp(java.lang.String registerIp) {
    	this.registerIp = registerIp;
    }

    public java.lang.String getRegisterIp() {
    	return this.registerIp;
    }
    public void setState(java.lang.String state) {
    	this.state = state;
    }

    public java.lang.String getState() {
    	return this.state;
    }
    public void setEmail(java.lang.String email) {
    	this.email = email;
    }

    public java.lang.String getEmail() {
    	return this.email;
    }
    public void setType(int type) {
    	this.type = type;
    }

    public int getType() {
    	return this.type;
    }

 
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RegUser) ) return false;
        RegUser castOther = (RegUser) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId()).toHashCode();
    }   
}
