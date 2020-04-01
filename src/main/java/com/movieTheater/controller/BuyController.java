package com.movieTheater.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.BuyDao;
import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Card;
import com.movieTheater.model.ShopCart;
import com.movieTheater.model.form.ShopCartForm;


@Controller
@Transactional
public class BuyController {

	@Autowired
	private SessionDao sessionDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private ShopCart shopCart;
	@Autowired
	private BuyDao buyDao;
	
	@PostMapping("/buy/ingressos")
	public ModelAndView enviaParaPagamento(ShopCartForm shopCartForm) {
		ModelAndView mv = new ModelAndView("redirect:/buy");
		shopCartForm.toIngressos(sessionDao, seatDao).forEach(shopCart::add);
		return mv;
	}
	
	@GetMapping("/buy")
	public ModelAndView formCompra(Card card) {
		ModelAndView mv = new ModelAndView("buy/pagamento");
		mv.addObject("shopCart", shopCart);
		return mv;
	}
	
	@PostMapping("/buy/buyr")
	public ModelAndView buyr(@Valid Card card, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		if(card.isValidated()) {
			buyDao.save(shopCart.toCompra());
			this.shopCart.clear();
		} else {
			result.rejectValue("expires", "Invalid");
			return formCompra(card);
		}
		return mv;
	}
	
}
