package realsun.webpos.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.litepal.annotation.Column;

public class OrderDetail extends BaseRecord{

	@Column(nullable = false)
	@JSONField(name="C3_512140250739")
	private String orderno;
	@JSONField(name ="C3_512140251614")
	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	@JSONField(name ="C3_512140253505")
	private int amount;
	@JSONField(name ="C3_512140252005")
	private float price;

	@JSONField(name ="C3_512140253223")
	private float totalprice;

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public PosOrder getPosOrder() {
		return posOrder;
	}

	public void setPosOrder(PosOrder posOrder) {
		this.posOrder = posOrder;
	}

	private PosOrder posOrder;
	public void setName(String name) {
			this.name = name;
		}
	public int getAmount() {
			return amount;
		}
	public void setAmount(int amount) {
			this.amount = amount;
		}
	public float getPrice() {
			return price;
		}
	public void setPrice(float price) {
			this.price = price;
		}

}
