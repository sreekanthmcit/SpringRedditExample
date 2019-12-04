package com.sreekanth.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sreekanth.springit.config.AuditorAwareImpl;
import com.sreekanth.springit.domain.Comment;
import com.sreekanth.springit.domain.Link;
import com.sreekanth.springit.domain.User;
import com.sreekanth.springit.repository.UserRepository;
import com.sreekanth.springit.service.BeanUtil;
import com.sreekanth.springit.service.CommentService;
import com.sreekanth.springit.service.LinkService;

//@RequestMapping("/links")
@Controller
public class LinkController {

	private LinkService linkService;
	
	private CommentService commentService;
	
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	public LinkController(LinkService linkService ,CommentService commentService,UserRepository userRepository) {
		this.linkService = linkService;
		this.commentService = commentService;
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkService.findAll());
		return "link/list";
	}

	@GetMapping("link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkService.findById(id);
		if (link.isPresent()) {
			Link curLink = link.get();
			Comment comment = new Comment();
			comment.setLink(curLink);
			model.addAttribute("comment", comment);
			model.addAttribute("link", curLink);
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
		User user = null;
		Optional<String> optionalemail = BeanUtil.getBean(AuditorAwareImpl.class).getCurrentAuditor();
		if(optionalemail.isPresent()) {
			Optional<User> optionalUser = userRepository.findByEmail(optionalemail.get());
			user = (optionalUser.isPresent())?optionalUser.get():null;
		}
		if(bindingResult.hasErrors()) {
			logger.info("Validation Errors were found while submiting a new link");
			link.setUser(user);
			model.addAttribute("link", link);
			return "link/submit";
		}else {
			link.setUser(user);
			linkService.save(link);
			logger.info("New Link Saved Successfully");
			redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}
		
		
	}
	@Secured({"ROLE_USER"})
	@PostMapping("/link/comments")
	public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
	    if( bindingResult.hasErrors() ) {
	        logger.info("Something went wrong.");
	    } else {
	        logger.info("New Comment Saved!");
	        commentService.save(comment);
	    }
	    return "redirect:/link/" + comment.getLink().getId();
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