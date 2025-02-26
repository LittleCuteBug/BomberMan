# Bài tập lớn OOP - Bomberman Game
___

## 1. Mô tả sơ lược:
- Đây là bài tập lớn số 2: Viết một phiên bản Java mô phỏng lại trò chơi [Bomberman](https://www.youtube.com/watch?v=mKIOVwqgSXM) kinh điển của NES, có thể dựa vào mã nguồn có sẵn (từ commit thứ 21).
- Bài tập này được thực hiện bởi 2 sinh viên: [Nguyễn Quang Huy](https://github.com/LittleCuteBug) (trưởng nhóm) và [Phạm Anh Cường](https://github.com/pacman-ctm). Cả 2 đều là sinh viên lớp K64-C-CLC, Trường Đại học Công nghệ - Đại học Quốc gia Hà Nội.

<img src="res/demo.png" alt="drawing" width="400"/>

## 2. Hướng dẫn chơi:
- Mở file bomberman.jar (có thể tìm được ở đường dẫn [target/bomberman.jar](https://github.com/LittleCuteBug/BomberMan/tree/master/target)).

## 3. Mô tả về các đối tượng trong trò chơi
Nếu bạn đã từng chơi Bomberman, bạn sẽ cảm thấy quen thuộc với những đối tượng này. Chúng được được chia làm hai loại chính là nhóm đối tượng động (*Bomber*, *Enemy*, *Bomb*) và nhóm đối tượng tĩnh (*Grass*, *Wall*, *Brick*, *Item*).

- ![](res/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 
- ![](res/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](res/sprites/explosion_horizontal.png) được tạo ra.


- ![](res/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](res/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
- ![](res/sprites/item_brick.png) *Item Brick* là đối tượng được xem là các Brick nhưng chưa item, phá huỷ Item Brick mới có thể sử Item ẩn dưới Item Brick đó.

- ![](res/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau ItemBrick và chỉ hiện ra khi ItemBrick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp.
- ![](res/sprites/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn).
- ![](res/sprites/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
- ![](res/sprites/powerup_bombpass.png) *BombPassItem* Khi sử dụng Item này, Bomber sẽ đi được xuyên qua Bomb, thay vì bị Bomb chặn lại như ban đầu. BombPassItem bắt đầu xuất hiện từ màn (level/stage) 4.
- ![](res/sprites/powerup_flamepass.png) *FlamePassItem* Khi sử dụng Item này, Bomber có thể đi xuyên qua các đối tượng *Flame*, điều này giúp Bomber có thể tránh bị kẹt nhưng không giúp Bomber bất tử trước Flame. FlamePassItem cũng bắt đầu xuất hiện từ màn 4.
- ![](res/sprites/powerup_wallpass.png) *WallPassItem* Khi sử dụng Item này, Bomber có thể đi xuyên các đối tượng Brick (nhưng không thể ăn được Item ẩn dưới Item Brick). WallPassItem cũng bắt đầu xuất hiện từ màn 4.

Có nhiều loại *Enemy* trong Bomberman. Thông tin của các Enemy được liệt kê dưới đây:

<img src="report/Enemy.png" alt="drawing" width="900"/>

Cụ thể:
- ![](res/sprites/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định. Balloom bắt đầu xuất hiện từ màn 1.
- ![](res/sprites/oneal_left1.png) *Oneal* có tốc độ di chuyển nhanh nhất và biết đuổi theo Bomber. Oneal bắt đầu xuất hiện từ màn 2.
- ![](res/sprites/doll_left1.png) *Doll* có tốc độ di chuyển ngang Oneal, có thể đặt bomb. Doll bắt đầu xuất hiện từ màn 3.
- ![](res/sprites/minvo_left1.png) *Minvo* có tốc độ di chuyển chậm nhất, có thể đặt bomb, có thể đuổi theo Bomber. Minvo bắt đầu xuất hiện từ màn 4.
- ![](res/sprites/kondoria_left1.png) *Kondoria* có tốc độ di chuyển trung bình, có thể đi xuyên Brick. Kondoria bắt đầu xuất hiện từ màn 5.
- ![](res/sprites/ovapi.png) *Ovapi* có tốc độ nhanh bằng Oneal, có thể đi xuyên Brick, có thể đuổi theo Bomber. Ovapi bắt đầu xuất hiện từ màn 6.
- ![](res/sprites/pass.png) *Pass* có tốc độ ngang Minvo, có thể đi xuyên Brick, có thể đặt bomb. Pass bắt đầu xuất hiện từ màn 7.
- ![](res/sprites/pontan.png) *Pontan* có tốc độ ngang Oneal, có thể đi xuyên Brick, có thể đặt bomb, có thể đuổi theo Bomber. Pontan bắt đầu xuất hiện từ màn 8.

## 4. Mô tả game play, xử lý va chạm và xử lý bom nổ
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và di chuyển đến Portal để có thể qua màn mới.
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb (của cả mình và của cả Enemy) nổ. Lúc đấy Bomber trở về vị trí đầu tiên và tiếp tục màn chơi của mình.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb (của Bomber) nổ. 
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm![](res/sprites/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên![](res/sprites/explosion_vertical.png)/dưới![](res/sprites/explosion_vertical.png)/trái![](res/sprites/explosion_horizontal.png)/phải![](res/sprites/explosion_horizontal.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.

## 5. Yêu cầu chung **(Hoàn thành)**
- Có thể chơi được ít nhất cho một màn chơi (chiến thắng một màn chơi)
- Có thể thay đổi được tệp cấu hình khác cho màn chơi (tương tự mẫu cho trước)

## 6. Nhiệm vụ:
- Gói bắt buộc (+8đ)
1. Thiết kế cây thừa kế cho các đối tượng game +2đ **(Hoàn thành, xem file [Entity.png](https://github.com/LittleCuteBug/BomberMan/blob/master/Entity.png))**
2. Xây dựng bản đồ màn chơi từ tệp cấu hình (có mẫu tệp cấu hình, xem [tại đây](https://raw.githubusercontent.com/bqcuong/bomberman-starter/starter-2/res/levels/Level1.txt)) +1đ **(Hoàn thành)**.
3. Di chuyển Bomber theo sự điều khiển từ người chơi +1đ **(Hoàn thành)**.
4. Tự động di chuyển các Enemy +1đ **(Hoàn thành)**.
5. Xử lý va chạm cho các đối tượng Bomber, Enemy, Wall, Brick, Bomb +1đ **(Hoàn thành)**.
6. Xử lý bom nổ +1đ **(Hoàn thành)**.
7. Xử lý khi Bomber sử dụng các Item và khi đi vào vị trí Portal +1đ **(Hoàn thành)**.

- Gói tùy chọn (tối đa +2đ)
1. Nâng cấp thuật toán tìm đường cho Enemy +0.5đ **(Hoàn thành)**.
   Cài đặt thêm các loại Enemy khác: +0.25đ cho mỗi loại enemy **(có thêm 6 enemy, ngoài Balloom với Oneal đã có còn có Doll, Minvo, Kondoria, Ovapi, Pass, Pontan)**.
2. Cài đặt thuật toán AI cho Bomber (tự chơi) +1đ 
3. Xử lý hiệu ứng âm thanh (thêm music & sound effects) +1đ **(Hoàn thành)**.
4. Phát triển hệ thống server-client để nhiều người có thể cùng chơi qua mạng LAN hoặc Internet +1đ
5. Những ý tưởng khác:
   * Tự động sinh bản đồ mới.
   * Cho các SmartEnemy có khả năng đặt bom và/hoặc di chuyển nhanh hơn khi ở gần Enemy.
   * Thuật toán BFS tìm đường cho Enemy di chuyển đến Bomber.
