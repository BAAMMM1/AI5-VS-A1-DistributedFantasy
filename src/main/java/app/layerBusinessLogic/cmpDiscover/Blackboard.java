package app.layerBusinessLogic.cmpDiscover;

import java.net.InetAddress;

public class Blackboard {

	private int port;
	private InetAddress ip;

	public Blackboard(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String getUrl() {
		return "http:/" + ip + ":" + port;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Blackboard that = (Blackboard) o;

		if (port != that.port) return false;
		return ip != null ? ip.equals(that.ip) : that.ip == null;
	}

	@Override
	public int hashCode() {
		int result = port;
		result = 31 * result + (ip != null ? ip.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Blackboard{" +
				"port=" + port +
				", ip=" + ip +
				'}';
	}
}
