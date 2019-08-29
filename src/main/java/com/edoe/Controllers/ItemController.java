package com.edoe.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.Model.DescritorItem;
import com.edoe.Model.Doacao;
import com.edoe.Model.Item;
import com.edoe.Model.Matching;
import com.edoe.Model.TipoUsuario;
import com.edoe.Service.DescritorItemService;
import com.edoe.Service.ItemService;

@RestController
@RequestMapping({ "/item" })
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private DescritorItemService descritorService;

	@PostMapping("/itemtemdoador/{usuarioId}")
	public void cadastrarItemDoador(@PathVariable String usuarioId, @RequestBody Item item) {
		try {
			item.setTipoUsuario(TipoUsuario.DOADOR);
			item.setIdDoador(usuarioId);
			itemService.cadastrarItem(item);
			itemService.cadastraItemDoador(usuarioId, item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/itemreceptor/{usuarioId}")
	public void cadastrarItemReceptor(@PathVariable String usuarioId, @RequestBody Item item) {
		try {
			item.setTipoUsuario(TipoUsuario.RECEPTOR);
			item.setIdDoador("");
			itemService.cadastrarItem(item);
			itemService.cadastraItemReceptor(usuarioId, item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PutMapping("/itemreceptor/{idUsuario}")
	public void atualizarItemReceptor(@PathVariable String idUsuario, @RequestBody Item item) throws Exception {
		itemService.atualizaItemReceptor(idUsuario, item);
	}

	@DeleteMapping("itemreceptor/{id}/{Id}")
	public void deletarItemReceptor(@PathVariable String idUsuario, @PathVariable String idItem) throws Exception {
		itemService.deletarItemReceptor(idUsuario, idItem);
	}

	@GetMapping("/itensOrdemDesc")
	public List<Item> getItensSortDesc() {
		return this.itemService.getItensSortDesc();
	}

	@GetMapping("/itensOrdemQuant")
	public List<Item> getItensSortQuant() {
		return this.itemService.getItensSortQuant();
	}

	@GetMapping("/itensNecessarios")
	public List<Item> getItensNecessarios() {
		return this.itemService.getItensNecessarios();
	}

	@GetMapping("/encontrar/{id}")
	public Item findById(@PathVariable String id) {
		return this.itemService.findById(id);
	}

	@GetMapping()
	public List<Item> retornaItens() {
		return this.itemService.retornaItens();
	}

	@DeleteMapping("/itemdoador/{id}/{Id}")
	public void deletarItemDoador(@PathVariable String id, @PathVariable String Id) throws Exception {
		itemService.removerItemDoacao(id, Id);
	}

	@PutMapping("/itemdoador/{idUsuario}")
	public void atualizarItemDoador(@PathVariable String idUsuario, @RequestBody Item item) throws Exception {
		itemService.atualizaItemDoador(idUsuario, item);
	}

	@DeleteMapping()
	public void deletarTodosItens() {
		itemService.deletarTodos();
	}

	@GetMapping("/matching/{idReceptor}/{idItem}")
	public List<Matching> matching(@PathVariable String idReceptor, @PathVariable String idItem) {
		return this.itemService.matching(idReceptor, idItem);
	}
	
	@PostMapping("/doacao/{idItemNecessario}/{idItemDoador}/{idReceptor}")
	public void realizaDoacao(@PathVariable String idItemNecessario, @PathVariable String idItemDoador, @PathVariable String idReceptor) {
		itemService.realizaDoacao(idItemNecessario, idItemDoador, idReceptor);
		
	}
	// DESCRITORES

	@PostMapping("/adicionadescritor")
	public void cadastrarDescritor(@RequestBody DescritorItem descritor) {
		descritorService.insereDescritor(descritor);
	}

	@GetMapping("/descritoresQuant")
	public String retornaItemDesc() {
		return this.itemService.retornaItemDesc();
	}

	@GetMapping("/descritores")
	public List<DescritorItem> getDescritores() {
		return descritorService.carregaDescritores();
	}
	
	@DeleteMapping("/descritores")
	public void deletaDescritores() {
		descritorService.deletaDecritores();
	}

	@GetMapping("/doacao")
	public List<Doacao> retornaDoacoes(){
		return itemService.retornaDoacoes();
	}
}
