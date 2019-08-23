package com.edoe.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Document(collection = "ITEM")
public class Item {
	@Id
	private ObjectId Id;

	private DescritorItem descritorItem;
	
	private List<String> tags;
	
	private int quantidade;

	public Item(ObjectId id, DescritorItem descritorItem, int quantidade, String tags) {
		super();
		Id = id;
		this.descritorItem = descritorItem;
		this.quantidade = quantidade;
	}

	public ObjectId getId() {
		return Id;
	}

	public void setId(ObjectId id) {
		Id = id;
	}

	public DescritorItem getDescritorItem() {
		return descritorItem;
	}

	public void setDescritorItem(DescritorItem descritorItem) {
		this.descritorItem = descritorItem;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String toStringDescQuant() {
		return quantidade + " - " + descritorItem.getDescricao();
	}

	
	
	

}