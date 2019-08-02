package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Subscription;

import java.util.List;
import java.util.Map;

/**
 * @author Smit Saraiya
 */
public interface IAdminDAO {
  User getAdmin() throws DAOException;
  List<Subscription> fetchAllSubscriptionRequests() throws DAOException;
  void approveSubscription(int subscription_id) throws DAOException;
  Map<String,Object> getPostsByMaxReports() throws DAOException;
}
