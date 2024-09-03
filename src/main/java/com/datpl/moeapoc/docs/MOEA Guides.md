# 1. Cách định nghĩa một Problem
Trong MOEA Framework, một `Problem` là một bài toán tối ưu hóa mà bạn muốn giải quyết. Nó liên quan trực tiếp đến khái niệm `Chromosome` và `Fitness Function`:

- `Chromosome` (Solution): Trong MOEA Framework, một giải pháp (`Chromosome`) được biểu diễn bằng một vector chứa các biến (`variables`). Mỗi biến đại diện cho một gene trên nhiễm sắc thể.
- `Fitness Function`: Được định nghĩa bằng cách tính toán các hàm mục tiêu (`objective functions`) cho mỗi `solution`. MOEA Framework cho phép bạn định nghĩa các hàm mục tiêu và các ràng buộc của bài toán.

# 2. Các loại Variable dựng sẵn và cách định nghĩa Custom Variable
MOEA Framework cung cấp nhiều loại biến (variable) dựng sẵn như:

- `RealVariable`: Biến thực, tương ứng với các giá trị liên tục.
- `BinaryVariable`: Biến nhị phân, tương ứng với các giá trị 0 hoặc 1.
- `Permutation`: Biến hoán vị, thường sử dụng cho các bài toán như sắp xếp hoặc Traveling Salesman Problem (TSP).

Cách định nghĩa Custom Variable: Nếu các biến dựng sẵn không phù hợp, bạn có thể tạo các biến tùy chỉnh bằng cách kế thừa lớp `Variable` và định nghĩa các phương thức cần thiết như `copy()`, `getLowerBound()`, và `getUpperBound()`.

# 3. Truyền data vào Problem và execute nó với một Algorithm nào đó
Sau khi định nghĩa problem, bạn có thể truyền dữ liệu vào problem (chẳng hạn như các ràng buộc hoặc giá trị biến ban đầu) và thực thi với một thuật toán cụ thể.

```java
// xác định vấn đề
Problem problem = new CustomProblem(); 
// chọn thuật toán
Algorithm algorithm = new NSGAII(problem);
// chạy thuật toán
NondominatedPopulation result = algorithm.execute();
// show kết quả
for (Solution solution : result) {
    System.out.println(solution.getObjective(0)); // Giá trị của mục tiêu đầu tiên
    System.out.println(solution.getObjective(1)); // Giá trị của mục tiêu thứ hai (nếu có)
    // Tiếp tục lấy giá trị của các mục tiêu khác nếu cần
}
```

# 4. Các thuộc tính khi chạy Problem (Properties)
MOEA Framework cho phép cấu hình các thuộc tính (`properties`) khi chạy problem. Điều này bao gồm việc thiết lập các thông số như:

- Số thế hệ (`Generations`): Số lần lặp lại của thuật toán.
- Kích thước quần thể (`Population Size`): Số lượng cá thể trong mỗi thế hệ.
- Tần suất lai ghép và đột biến (`Crossover` and `Mutation Rates`): Tần suất của các thao tác này trong quá trình tiến hóa.

# Built-in Algo:

- `NSGA-II` (Non-dominated Sorting Genetic Algorithm II): Sử dụng việc sắp xếp không trội để giữ lại các giải pháp Pareto tốt nhất trong quần thể.
- SPEA2 (Strength Pareto Evolutionary Algorithm 2): Sử dụng một kho lưu trữ để lưu trữ các giải pháp không bị trội và tính toán độ mạnh của mỗi giải pháp dựa trên số lượng giải pháp nó chiếm ưu thế.