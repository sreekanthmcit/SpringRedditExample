package com.sreekanth.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.repository.LinkRepository;

//@RequestMapping("/links")
@Controller
public class LinkController {

	private LinkRepository linkRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkRepository.findAll());
		return "link/list";
	}

	@GetMapping("link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkRepository.findById(id);
		if (link.isPresent()) {
			model.addAttribute("link", link.get());
			model.addAttribute("success", model.containsAttribute("success"));
			return "link/view";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			logger.info("Validation Errors were found while submiting a new link");
			model.addAttribute("link", link);
			return "link/submit";
		}else {
			linkRepository.save(link);
			logger.info("New Link Saved Successfully");
			redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}
		
		
	}

	/*
	 * foo.html code Base
	 * 
	 * 
	 * 
	 * @Autowired public LinkController(LinkRepository linkRepository) {
	 * this.linkRepository = linkRepository; }
	 * 
	 * @GetMapping("/") public List<Link> list() { return linkRepository.findAll();
	 * }
	 * 
	 * @GetMapping("/foo") public String foo(Model model) {
	 * model.addAttribute("pageTitle", "Sreekanth Reddit"); return "foo"; }
	 * 
	 * @PostMapping("/create") public Link create(@ModelAttribute Link link) {
	 * return linkRepository.save(link); }
	 * 
	 * @GetMapping("/{id}") public Optional<Link> read(@PathVariable Long id) {
	 * return linkRepository.findById(id); }
	 * 
	 * @PutMapping("/{id}") public Link update(@ModelAttribute Link link) { return
	 * linkRepository.save(link); }
	 * 
	 * @DeleteMapping("/delete") public void delete(@PathVariable Long id) {
	 * linkRepository.deleteById(id); }
	 */
}