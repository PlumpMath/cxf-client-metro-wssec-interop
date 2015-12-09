/**
 * Copyright (C) 2012-2015 Thales Services SAS.
 *
 * This file is part of AuthZForce.
 *
 * AuthZForce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AuthZForce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AuthZForce.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package com.thalesgroup.authzforce.rest.jaxrs.api.impl;

import org.ow2.authzforce.jaxrs.api.PolicySetResource;
import org.ow2.authzforce.jaxrs.api.PolicySetResources;
import org.ow2.authzforce.xmlns.rest.api.Resources;
import org.w3._2005.atom.Link;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySet;

public class PolicySetResourcesImpl implements PolicySetResources
{

	@Override
	public Link addPolicySet(PolicySet policyset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resources getPolicySets(String policySetId, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PolicySetResource getPolicySetResource(String policyUID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
