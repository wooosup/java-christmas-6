package christmas;

import christmas.controller.Controller;
import christmas.repository.DataLoader;
import christmas.repository.ProductRepository;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        DataLoader loader = new DataLoader(productRepository);

        loader.load();

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        EventService eventService = new EventService();
        Controller controller = new Controller(inputView, outputView, eventService, productRepository);

        controller.run();
    }
}
