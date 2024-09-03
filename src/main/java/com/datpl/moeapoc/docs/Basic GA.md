# Thuật toán di truyền (Genetic Algorithm - GA)

## 1. Các bước/phases trong thuật toán tiến hóa
Thuật toán di truyền thường gồm các bước cơ bản sau:

- Khởi tạo (Initialization): Tạo một quần thể ban đầu gồm các cá thể `(các giải pháp tiềm năng)`.
- Đánh giá (Evaluation): Tính toán giá trị của hàm thích nghi `(fitness function)` cho mỗi cá thể.
- Chọn lọc (Selection): Lựa chọn các cá thể từ quần thể hiện tại dựa trên giá trị `fitness` để sinh sản.
- Lai ghép (Crossover): Kết hợp hai cá thể `(parent)` để tạo ra các cá thể mới (offspring).
- Đột biến (Mutation): Thay đổi ngẫu nhiên một phần của cá thể để tạo ra sự đa dạng.
- Cập nhật quần thể (Population Update): Thay thế quần thể cũ bằng quần thể mới.
- Điều kiện dừng (Termination Condition): Kiểm tra điều kiện dừng (có thể dựa trên số thế hệ hoặc đạt được giá trị `fitness` mong muốn).

## 2. Quần thể (Population)
Quần thể là tập hợp các cá thể, mỗi cá thể đại diện cho một giải pháp tiềm năng cho bài toán. Kích thước của quần thể thường cố định trong suốt quá trình tiến hóa. Một quần thể đa dạng sẽ giúp quá trình tìm kiếm được hiệu quả hơn, tránh bị mắc kẹt ở các cực trị địa phương.

## 3. Nhiễm sắc thể (Chromosome) - Giải pháp (Solution)
Trong GA, mỗi cá thể trong quần thể được biểu diễn bởi một nhiễm sắc thể. Nhiễm sắc thể thường được mã hóa dưới dạng chuỗi nhị phân, chuỗi số nguyên hoặc chuỗi thực tùy thuộc vào bài toán cụ thể. Một nhiễm sắc thể đại diện cho một giải pháp tiềm năng cho bài toán tối ưu hóa.

## 4. Hàm thích nghi (Fitness Function)
Hàm thích nghi đánh giá "độ tốt" của mỗi cá thể trong quần thể. Giá trị `fitness` cao hơn biểu thị một giải pháp tốt hơn cho bài toán. Mục tiêu của GA là tối ưu hóa hàm fitness này.

## 5. Chọn lọc bố mẹ (Parent Selection)
Quá trình chọn lọc bố mẹ dựa trên giá trị `fitness` của các cá thể. Các cá thể với giá trị `fitness` cao có xác suất được chọn làm bố mẹ cao hơn. Các phương pháp chọn lọc phổ biến bao gồm:

- Roulette Wheel Selection: Xác suất chọn cá thể tỉ lệ với giá trị `fitness` của nó.
- **Tournament Selection**: Chọn ngẫu nhiên một nhóm cá thể và chọn cá thể tốt nhất trong nhóm đó.
- Rank Selection: Các cá thể được xếp hạng và xác suất chọn phụ thuộc vào hạng.

## 6. Lai ghép (Crossover) và Đột biến (Mutation)
Lai ghép (Crossover): Quá trình kết hợp hai bố mẹ để tạo ra một hoặc nhiều con. Lai ghép tạo ra sự pha trộn của các đặc tính từ bố mẹ, giúp tìm kiếm giải pháp mới trong không gian tìm kiếm. Các phương pháp lai ghép phổ biến bao gồm:

- Single-point crossover: Chọn một điểm trên nhiễm sắc thể và hoán đổi phần sau điểm đó giữa hai bố mẹ.
- Multi-point crossover: Chọn nhiều điểm trên nhiễm sắc thể và hoán đổi các đoạn giữa những điểm đó.
- Đột biến (Mutation): Thay đổi ngẫu nhiên một hoặc nhiều gene trong nhiễm sắc thể. Đột biến giúp duy trì sự đa dạng di truyền trong quần thể và ngăn ngừa bị mắc kẹt ở cực trị địa phương. Ví dụ, với mã hóa nhị phân, đột biến có thể là thay đổi một bit từ 0 thành 1 hoặc ngược lại.

## 7. Điều kiện dừng (Terminal Condition)
Điều kiện dừng là tiêu chí xác định khi nào thuật toán sẽ kết thúc. Các điều kiện dừng phổ biến bao gồm:

- Đạt được một số thế hệ cố định.
- Giá trị fitness của cá thể tốt nhất đạt một ngưỡng nhất định.
- Sự đa dạng của quần thể giảm đến mức quá nhỏ (các cá thể trở nên quá giống nhau).