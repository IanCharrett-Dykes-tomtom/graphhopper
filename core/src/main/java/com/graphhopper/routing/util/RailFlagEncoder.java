package com.graphhopper.routing.util;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.util.EncodingManager.Access;
import com.graphhopper.util.PMap;

public class RailFlagEncoder extends CarFlagEncoder {
	
	
	public RailFlagEncoder(int speedBits, double speedFactor, int maxTurnCosts) {
		super(speedBits, speedFactor, maxTurnCosts);
	}

	public RailFlagEncoder(PMap properties) {
		this(5,5,0);
	}

	public RailFlagEncoder() {
		super();
	}
	
	@Override
	public Access getAccess(ReaderWay way) {
		if(way.getTag("railway") == null) {
    		if(way.hasTag("route", "railway","train")) {
    			return Access.WAY;
    		}
    		return Access.CAN_SKIP;
    	}
    	return Access.WAY;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}
	
	@Override
	protected double getSpeed(ReaderWay way) {
		if(way.hasTag("route", "railway","train"))
			return 20;
		if(way.getTag("railway")!=null) return 20;
		
		return super.getSpeed(way);
	}
	
}
