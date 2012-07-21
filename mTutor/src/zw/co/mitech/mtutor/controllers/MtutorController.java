package zw.co.mitech.mtutor.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zw.co.mitech.mtutor.service.RequestProcessor;
import zw.co.mitech.mtutor.util.Txt;
import zw.co.mitech.mtutor.util.TxtXml;

@Controller
public class MtutorController {
	
	@Autowired
	private RequestProcessor txtProcessor;
	
	
	@RequestMapping("/mtutor")
	public @ResponseBody TxtXml processRequest(HttpServletRequest request){
		Txt txt = new Txt(request);
		txt = txtProcessor.processTxtRequest(txt);
		txt.serialiseSession();
		return new TxtXml(txt);
	}

}
