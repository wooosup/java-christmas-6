package christmas.repository;

import static christmas.domain.ProductType.*;

import christmas.domain.Product;

public record DataLoader(ProductRepository productRepository) {

    public void load() {
        loadMenu();
    }

    private void loadMenu() {
        productRepository.save(Product.of("양송이수프", 6000, APPETIZER));
        productRepository.save(Product.of("타파스", 5500, APPETIZER));
        productRepository.save(Product.of("시저샐러드", 8000, APPETIZER));

        productRepository.save(Product.of("티본스테이크", 55000, MAIN));
        productRepository.save(Product.of("바비큐립", 54000, MAIN));
        productRepository.save(Product.of("해산물파스타", 35000, MAIN));
        productRepository.save(Product.of("크리스마스파스타", 25000, MAIN));

        productRepository.save(Product.of("초코케이크", 15000, DESSERT));
        productRepository.save(Product.of("아이스크림", 5000, DESSERT));

        productRepository.save(Product.of("제로콜라", 3000, DRINK));
        productRepository.save(Product.of("레드와인", 60000, DRINK));
        productRepository.save(Product.of("샴페인", 25000, DRINK));
    }

}
