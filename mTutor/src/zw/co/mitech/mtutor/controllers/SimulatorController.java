package zw.co.mitech.mtutor.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zw.co.mitech.mtutor.service.RequestProcessor;
import zw.co.mitech.mtutor.util.Txt;

@Controller
@RequestMapping("/simulator")
public class SimulatorController {

	@Autowired
	private RequestProcessor txtProcessor;

	@RequestMapping("/page")
	public ModelAndView showSimulatorPage() {
		ModelAndView modelAndView = new ModelAndView("simulator");
		modelAndView.addObject("txt", new Txt());
		return modelAndView;
	}

	@RequestMapping("/process")
	public ModelAndView simulate(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("simulator");
		Txt txt = new Txt(request);
		txt = txtProcessor.processTxtRequest(txt);
		txt.serialiseSession();
		
		if (txt.getMessage() != null) {
			modelAndView.addObject("msgLength", txt.getMessage().length());
		}
		modelAndView.addObject("txt", txt);
		return modelAndView;
	}

}
