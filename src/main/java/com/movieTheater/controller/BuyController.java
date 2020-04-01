package com.movieTheater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.SessionDao;
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
//	@Autowired
//	private CompraDao compraDao;
	
	@PostMapping("/compra/ingressos")
	public ModelAndView enviaParaPagamento(ShopCartForm shopCartForm) {
		ModelAndView mv = new ModelAndView("redirect:/compra");
		shopCartForm.toIngressos(sessionDao, seatDao).forEach(shopCart::add);
		return mv;
	}
	
	@GetMapping("/compra")
	public ModelAndView formCompra() {
		ModelAndView mv = new ModelAndView("compra/pagamento");
		mv.addObject("shopCart", shopCart);
		return mv;
	}
//	
//	@PostMapping("/compra/comprar")
//	public ModelAndView comprar(@Valid Cartao cartao, BindingResult result) {
//		ModelAndView mv = new ModelAndView("redirect:/");
//		
//		if(cartao.isValido()) {
//			compraDao.save(carrinho.toCompra());
//			this.carrinho.limpa();
//		} else {
//			result.rejectValue("vencimento", "Vencimento inválido");
//			return formCompra(cartao);
//		}
//		return mv;
//	}
	
}
