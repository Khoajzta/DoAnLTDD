fun parseSelectedProducts(selectedProductsString: String?): List<Pair<Int, Int>> {
    return selectedProductsString?.split(",")?.map {
        val parts = it.split(":")
        val maSanPham = parts[0].toInt()  // Mã sản phẩm
        val soLuong = parts[1].toInt()    // Số lượng
        Pair(maSanPham, soLuong)
    } ?: emptyList()
}


