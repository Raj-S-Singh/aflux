/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package de.tum.in.aflux.component.control.actor;

import java.util.Map;

import de.tum.in.aflux.component.control.ControlToolConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

/**
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class RunNTimesToolActor extends AbstractAFluxActor {

	public RunNTimesToolActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,
			Map<String, String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner, properties,-1);

	}
	
	public RunNTimesToolActor() {
		this("-1",null,null,null);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		int times=Integer.parseInt(this.getProperty(ControlToolConstants.TIMES));
		int delay=Integer.parseInt(this.getProperty(ControlToolConstants.DELAY));
		if (delay<0) {
			delay=0;
		}
		
		String data=this.getProperty(ControlToolConstants.DATA).toUpperCase();
		int totalDelay=0;
		if (times>0) {
		    for (int i =0;i<times; i++) {
		    	totalDelay+=delay;
		    	Object value;
		    	if (data.equals("INDEX")) {
		    		value=String.valueOf(i);
		    	} else {
		    		value=message;
		    	}
		    	this.setOutput(1, value,totalDelay);
		    }
		} else {
			
		}
	}

}