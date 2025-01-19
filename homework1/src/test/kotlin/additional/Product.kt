package additional

data class Product(val name: String, val price: Double, val inStock: Boolean = true)

fun main() {
    val product = Product("Laptop", 1200.0)
    val product2 = Product("Smartphone", 1000.0, false)
    println("Laptop: $product \nSmart: $product2")

    val product3 = product.copy(price = 1500.0)
    println(product3)

    val (name, price, stock) = product3
    println("$name $price $stock")

    val product4 = Product("Camera", 1600.0)
    val product5 = Product("Camera", 1600.0)
    println(product4 == product5)
}