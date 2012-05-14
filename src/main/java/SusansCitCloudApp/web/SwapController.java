package SusansCitCloudApp.web;

import SusansCitCloudApp.Swap;
import SusansCitCloudApp.jdbcSwapRepository;
import SusansCitCloudApp.Swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("swaps")
@Controller
public class SwapController {

	@Autowired
	private jdbcSwapRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public void listTodos(Model model) {
		model.addAttribute("swaps", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createTodo(Model model, @RequestParam String text) {
		Swap swap = new Swap();
		swap.setText(text);
		repo.save(swap);
		model.addAttribute("swaps", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteTodo(Model model, @RequestParam int swapId) {
		repo.delete(swapId);
		model.addAttribute("swaps", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateSwap(Model model, @RequestParam int swapId) {
		Swap swap = repo.get(swapId);
		swap.setDone(!swap.isDone());
		repo.update(swap);
		model.addAttribute("swaps", repo.getAll());
	}

}
