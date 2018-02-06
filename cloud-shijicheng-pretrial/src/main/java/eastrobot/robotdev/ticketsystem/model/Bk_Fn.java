package eastrobot.robotdev.ticketsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Bk_Fn {
	private int id;
	private String name;
	private int parent_id;
	private String description;
	private String url;
	private String icon;
	private List<Bk_Fn> child = new ArrayList<Bk_Fn>();
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getParent_id() {
		return parent_id;
	}
	
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Bk_Fn> getChild() {
		return child;
	}

	public void setChild(List<Bk_Fn> child) {
		this.child = child;
	}
}
