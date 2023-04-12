package br.com.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.MarcaModel;
import br.com.fiap.repository.MarcaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/marca")
public class MarcaController {

	private static final String MARCA_FOLDER = "marca/";

	@Autowired
	public MarcaRepository repository;

	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("marcas", repository.findAll());
		return MARCA_FOLDER + "marcas";
	}

	@GetMapping("/form")
	public String open(@RequestParam String page, @RequestParam(required = false) Long id,
			@ModelAttribute("marcaModel") MarcaModel marcaModel, Model model) {
		if (page.equals("marca-editar")) {
			model.addAttribute("marcaModel", repository.findById(id));
		}

		return MARCA_FOLDER + page;
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("marca", repository.findById(id));
		return MARCA_FOLDER + "marca-detalhe";
	}

	@PostMapping()
	public String save(@Valid MarcaModel marca, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return MARCA_FOLDER + "marca-nova";
		}

		repository.save(marca);
		redirectAttributes.addFlashAttribute("messages", "Marca cadastrada com sucesso!");
		return "redirect:/marca";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, Model model, @Valid MarcaModel marca, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		marca.setIdMarca(id);
		repository.update(marca);

		redirectAttributes.addFlashAttribute("messages", "Marca atualizada com sucesso!");
		model.addAttribute("marcas", repository.findAll());
		
		return "redirect:/marca";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		boolean resultado = repository.deleteById(id);

		String message = "Marca deletada com sucesso!";
		if (!resultado) {
			message = "Erro ao deletar a marca, ela está sendo usada em outro produto...";
		}
		redirectAttributes.addFlashAttribute("messages", message);
		return "redirect:/marca";
	}

}
