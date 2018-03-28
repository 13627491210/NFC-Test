package realsun.webpos.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Album extends DataSupport {
	
    @Column(unique = true, defaultValue = "unknown")
    private String name;
	
    private float price;
	
    private byte[] cover;
	
    private List<Song> songs = new ArrayList<Song>();

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}

   
}