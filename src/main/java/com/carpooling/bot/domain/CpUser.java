/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carpooling.bot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "cp_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpUser.findAll", query = "SELECT c FROM CpUser c"),
    @NamedQuery(name = "CpUser.findByUserId", query = "SELECT c FROM CpUser c WHERE c.userId = :userId"),
    @NamedQuery(name = "CpUser.findByBotUserId", query = "SELECT c FROM CpUser c WHERE c.botUserId = :botUserId"),
    @NamedQuery(name = "CpUser.findByConversationId", query = "SELECT c FROM CpUser c WHERE c.conversationId = :conversationId"),
    @NamedQuery(name = "CpUser.findByLastMessageSent", query = "SELECT c FROM CpUser c WHERE c.lastMessageSent = :lastMessageSent"),
    @NamedQuery(name = "CpUser.findByLastMessageReceived", query = "SELECT c FROM CpUser c WHERE c.lastMessageReceived = :lastMessageReceived"),
    @NamedQuery(name = "CpUser.findByTxUser", query = "SELECT c FROM CpUser c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "CpUser.findByTxHost", query = "SELECT c FROM CpUser c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "CpUser.findByTxDate", query = "SELECT c FROM CpUser c WHERE c.txDate = :txDate")})
public class CpUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bot_user_id")
    private String botUserId;
    @Column(name = "conversation_id")
    private Integer conversationId;
    @Size(max = 250)
    @Column(name = "last_message_sent")
    private String lastMessageSent;
    @Size(max = 250)
    @Column(name = "last_message_received")
    private String lastMessageReceived;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CpPerson personId;

    public CpUser() {
    }

    public CpUser(Integer userId) {
        this.userId = userId;
    }

    public CpUser(Integer userId, String botUserId, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.botUserId = botUserId;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public void setLastMessageSent(String lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }

    public String getLastMessageReceived() {
        return lastMessageReceived;
    }

    public void setLastMessageReceived(String lastMessageReceived) {
        this.lastMessageReceived = lastMessageReceived;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public CpPerson getPersonId() {
        return personId;
    }

    public void setPersonId(CpPerson personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpUser)) {
            return false;
        }
        CpUser other = (CpUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carpooling.bot.domain.CpUser[ userId=" + userId + " ]";
    }
    
}
