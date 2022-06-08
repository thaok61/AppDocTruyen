package com.me.appdoctruyen.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.me.appdoctruyen.data.dao.BookDao;
import com.me.appdoctruyen.data.dao.UserDao;
import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.data.models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract BookDao bookDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.

                UserDao userDao = INSTANCE.userDao();
                User adminUser = new User("admin", "Thao", "Vu", "admin@gmail.com",
                        "admin", 0, true);
                User user1 = new User("user1", "Thao", "Vu", "user1@gmail.com",
                        "user1");
                User user2 = new User("user2", "Thao", "Vu", "user2@gmail.com",
                        "user2");
                userDao.insertAll(adminUser, user1, user2);

                BookDao bookDao = INSTANCE.bookDao();
                bookDao.insertAll(book1, book2, book3);
            });
        }
    };

    private static final Book book1 = new Book("Dân Gian Việt Nam", "Cáo Cụt Đuôi",
            "Một con Cáo bị mắc bẫy, sau nhiều lần giật mạnh đau đớn vùng vẫy thoát ra được, bèn chạy đi. Nhưng nó phải bỏ lại cái đuôi đẹp đẽ của nó lại trong bẫy.\n" +
                    "\n" +
                    "Trong một thời gian dài, nó không dám gặp mặt những con Cáo khác, vì nó biết rất rõ rằng, tất cả lũ Cáo kia sẽ lấy nó làm trò cười, chế giễu và đùa cợt sau lưng nó. Nhưng nó sống một mình cũng khó, nó cuối cùng cũng nghĩ ra được một kế để giải quyết cái vấn đề hóc búa này.\n" +
                    "\n" +
                    "Nó kêu gọi triệu tập một cuộc họp mặt tất cả lũ Cáo, nói rằng nó có một vài điều quan trọng cần phải nói cho cả làng biết.\n" +
                    "\n" +
                    "Khi chúng đã đến tham dự đông đủ, con Cáo cụt đuôi đứng dậy và diễn thuyết một hồi rất lâu về việc những con Cáo đã mắc nạn chỉ vì cái đuôi của mình.\n" +
                    "\n" +
                    "Con này thì đã bị chó săn vồ vì khi chạy đuôi bị vướng vào bụi gai. Con kia thì không thể chạy đủ nhanh để thoát vì cái đuôi quá nặng nề. Ngoài ra, như ai cũng đã biết, nó nói, rằng con người bây giờ đi săn Cáo cũng chỉ là muốn có được cái đuôi Cáo, họ cắt đuôi để treo làm chiến tích đi săn thú. Với những chứng cứ về tính nguy hiểm và vô dụng của cái đuôi, Cáo Già nói, nó khuyên tất cả các con Cáo nên về cắt đuôi đi, nếu muốn được sống cho an toàn.\n" +
                    "\n" +
                    "Khi nó vừa nói xong, một cụ Cáo đứng dậy, nói và cười mỉm:\n" +
                    "\n" +
                    "“Lão Cáo, xin lão vui lòng quay mặt đi chỗ khác một chút, rồi chúng tôi sẽ trả lời lão.”\n" +
                    "\n" +
                    "Khi con Cáo Cụt Đuôi tội nghiệp quay mặt đi, cả làng Cáo ồ lên cười và la ó, lúc đó Cáo Cụt Đuôi biết là có thuyết phục lũ Cáo kia bỏ cái đuôi của chúng thì cũng chẳng có ích gì nữa.\n" +
                    "\n" +
                    "Lời bàn: Đừng bao giờ nghe lời những người không muốn bạn hơn họ.", "", "");

    private static final Book book2 = new Book("Dân Gian Việt Nam", "Ông già họ Lê",
            "Ngày xưa có một ông già góa vợ họ Lê. Nhà ông tiền kho bạc đụn không sao tiêu hết. Nhưng ông lại không có con trai, chỉ sinh độc một mụn con gái, lớn lên, gả cho một anh chàng con nhà gia thế trong vùng. Chàng rể của ông người họ Bùi. Hắn lấy con gái ông là cốt nhìn vào gia tài kếch sù của ông. Về phía ông già họ Lê cũng bụng bảo dạ:\n" +
                    "\n" +
                    "– “Ta nay tuổi đã ngoài sáu mươi khó có thể có con được nữa chưa nói là con trai. Vậy thì con nào cũng là con thôi!”.\n" +
                    "\n" +
                    "Từ đó ông có ý định cho con gái mình ăn thừa tự. Khi đứa cháu ngoại của ông sinh ra, ý đó lại thêm quyết.\n" +
                    "\n" +
                    "Một hôm ông sang nhà con rể chơi. Đứa cháu của ông lúc này đã lên ba tuổi. Giữa lúc hai ông thông gia đang chuyện trò với nhau ở ngoài vườn thì đứa bé đã được mẹ nó bảo ra mời ông vào ăn cơm. Nó bập bẹ:\n" +
                    "\n" +
                    "“Mời ông vào ăn cơm”.\n" +
                    "\n" +
                    "Ông già họ Lê hỏi đùa nó:\n" +
                    "\n" +
                    "– “Cháu mời ông nào?”.\n" +
                    "\n" +
                    "Đứa bé chỉ vào ông nội của nó là người mà nó quen thuộc. Ông già họ Bùi hỏi lại:\n" +
                    "\n" +
                    "– “Cháu mời ông nào nữa?”.\n" +
                    "\n" +
                    "Đứa bé trả lời: – “Không”.\n" +
                    "\n" +
                    "Ông lão trỏ tay vào ông ngoại nó và hỏi: – “Thế cháu có mời ông này không?”.\n" +
                    "\n" +
                    "Đứa bé lắc đầu chạy về phía ông nội nó. Bố nó bảo đến mấy lần, nó vẫn không chịu mời. Ông nội nó nói đùa:\n" +
                    "\n" +
                    "– “Ông ấy có tiền bạc nhiều lắm, cháu có mời ông, ông sẽ dành cho mà tiêu, nếu cháu không mời, ông không cho nữa”.\n" +
                    "\n" +
                    "Chung quy đứa bé vì lạ đối với ông ngoại nó nên không chịu nghe lời. Tuy việc không có gì đáng kể nhưng bụng ông già họ Lê cảm thấy không vui.\n" +
                    "\n" +
                    "Lúc trở về, ông già họ Lê nghĩ lại câu chuyện vừa qua và nhiều chuyện cũ khác. Ông không giận đứa cháu ngoại, nhưng những chuyện ấy lại gợi cho ông rất nhiều về tâm tính của hai cha con ông già họ Bùi. Bụng bảo dạ:\n" +
                    "\n" +
                    "– “Hai cha con nhà này chỉ là một hạng thèm tiền khát của, và chúng có vẻ hả hê vì được phá phách tài sản của ta”.\n" +
                    "\n" +
                    "Rồi ông ngẫm nghĩ: – “Ôi! Bao nhiêu mồ hôi nước mắt của ta và ông cha ta mấy đời gom góp lại bỗng chốc lọt vào tay quân bất lương. Sao có thể như thế được nhỉ?”.\n" +
                    "\n" +
                    "Trong lòng ông nảy ra một sự bực bội ghen ghét, nó cứ làm ông băn khoăn day dứt. Ông toan đem phần lớn của cải cho họ và làng. Nhưng rồi lại gạt đi:\n" +
                    "\n" +
                    "– “Trừ phi không có con, chứ đã có con không ai làm thế bao giờ”.\n" +
                    "\n" +
                    "Trước kia ông có ý định ở vậy cho đến mãn đời. Nhưng bây giờ đã có sự thay đổi. Một ý nghĩ vụt lóe ra trong óc ông già – “Hay ta cứ lấy vợ rồi đến đâu hay đấy!”.\n" +
                    "\n" +
                    "Thế là chỉ ít lâu sau, người ta bỗng thấy ông nhờ mối đánh tiếng gá nghĩa với một người đàn bà góa chồng ở làng bên cạnh.\n" +
                    "\n" +
                    "Người vợ kế sau khi về làm bạn với ông được ba năm thì sinh một đứa con trai. Nỗi vui mừng của ông già họ Lê tưởng không gì hơn thế nữa. Ông chăm sóc cho con đến mức trong làng ngoài xóm phải đồn nhau: chưa một nhà quyền quý nào chăm được như vậy. Đã lo cho đứa bé trước mắt, ông lại còn lo cho nó mai sau. Thấy chàng rể hung bạo, nhiều lúc đến chơi nhà nhìn đứa con trai bé bỏng của mình bằng cặp mắt ganh ghét, ông đâm ra lo sợ. Ông thầm nghĩ: – “Mình nay đã gần đất xa trời. Nếu một mai khuất núi giữa lúc thằng bé hãy còn trứng nước thì nó không ngại gì mà không ra tay ám hại. Bởi vì nó thừa hiểu là theo luật của triều đình thì bao nhiêu tài sản của ta phải thuộc về thằng bé. Chao ôi, nguy hiểm cho tính mệnh con ta biết chừng nào?”. Vì thế, không một giây phút nào ông quên tìm cách bảo toàn cho đứa bé trong tương lai.\n" +
                    "\n" +
                    "Và rồi cuối cùng mọi người đều tỏ thái độ ngạc nhiên khi thấy ông già họ Lê thường tỏ thái độ lạnh nhạt đối với hai mẹ con người vợ kế. Người ta bàn tán rất nhiều về cái tên “Phi” mà ông đặt cho đứa bé. Một người nói:\n" +
                    "\n" +
                    "– “Chỉ có ngờ nó là con hàng xóm thì mới đặt tên như vậy!”\n" +
                    "\n" +
                    "– “Đúng, người khác tiếp. Chắc bây giờ ông lão mới hiểu ra là bảy mươi tuổi thì không thể nào có con được, lại là có con trai!”. Ông già họ Lê có nghe rất nhiều lời bàn tán về mình, nhưng ông chỉ cười thầm không nói gì cả.\n" +
                    "\n" +
                    "Cho đến ngày sắp mất, người ta vẫn không thấy ông nhắc nhở gì cho mọi người biết về việc thừa kế tài sản của mình. Chúc thư ông để lại chỉ vỏn vẹn có hai mươi mốt chữ Hán, mà tuồng như là viết dở dang trước khi tắt thở:\n" +
                    "\n" +
                    "“Thất thập nhi sinh phi ngô tử dã kỳ điền sản giao dữ tử tế ngoại nhân bất đắc tranh đoạt”.\n" +
                    "\n" +
                    "Vì vậy, sau khi chôn cất xong, lập tức chàng rể độc nhất của ông cứ chiếu theo chúc thư chiếm hết gia tài điền sản, chỉ để lại cho hai mẹ con thằng bé Phi một gian nhà nhỏ với vài thửa ruộng xấu.\n" +
                    "\n" +
                    "Thực ra, trước khi chết, ông già họ Lê đã gọi riêng hai mẹ con lại, trao cho một tượng gỗ. Tượng này tạc hình người, đặc biệt có một cánh tay giơ ra đằng trước, nhưng ngón tay lại chỉ quặt về phía bụng. Người vợ kế của ông không hiểu thế nào cà, chỉ một mực ngồi ôm lấy con mà khóc. Ông ghé vào tai an ủi:\n" +
                    "\n" +
                    "– Sau này nếu chúng nó tranh giành tư cơ của ta để lại, thì mẹ con cứ nhẫn nhục chịu vậy, đợi khi thằng Phi đã lớn khôn, hễ thấy có ông quan nào công minh bổ về hạt mình, bấy giờ hãy mang pho tượng này đưa lên quan phân xử.\n" +
                    "\n" +
                    "Dặn đoạn, ông tắt nghỉ.\n" +
                    "\n" +
                    "Cho đến ngày thằng Phi đã có vợ và sắp có con. Trong những năm qua, hai mẹ con từng gạt biết bao là nước mắt mỗi lúc nhìn thấy hoa lợi trên ruộng đất đều tuôn về nhà họ Bùi. Trong xóm ngoài làng không ai không ái ngại cho tình cảnh mẹ góa con côi không nơi nương tựa của họ. Nhưng người ta thấy bề ngoài, hai mẹ con vẫn tỏ ra yên lòng với số phận, không kêu ca, không oán trách. Như thế đã được mười tám năm trời.\n" +
                    "\n" +
                    "Một hôm người mẹ hay tin có một ông quan mới đổi đến huyện nhà nổi tiếng thanh liêm, xử kiện rất công bằng. Ông thường đi về các làng nghe ngóng dân tình và không bỏ sót những điều chướng tai gai mắt. Nghe tiếng đồn như thế, mẹ thằng Phi nhớ đến lời dặn của chồng, vội dắt con mang pho tượng lên quan. Cầm pho tượng, quan không hiểu ý tứ thế nào cả. Nghe người đàn bà thuật lại tình cảnh của mẹ con trong bao nhiêu năm, ông rất thương hại. Ông bèn bảo bà trở về, ba ngày nữa lại đến.\n" +
                    "\n" +
                    "Khi người đàn bà chào lạy ra về, mặc dầu trời đã khuya, quan vẫn cầm lấy pho tượng miên man suy nghĩ. Ông lật xuôi lật ngược. Tất cả đều không có gì khác thường. – “Ngón tay chỉ vào bụng. Phải chăng trong bụng có vật gì đây!”. Nghĩ vậy, ông hết lắc lại gõ. Qua tiếng kêu, ông nhận ra là ruột tượng rỗng. Lập tức ông đi lấy một con dao, dùng mũi dao nhọn thăm dò khắp nơi. Cuối cùng ông nạy được một miếng gỗ trám ở một bên nách pho tượng.\n" +
                    "\n" +
                    "– Mọi việc đều ở đây cả!\n" +
                    "\n" +
                    "Ông mừng rỡ kêu lên. Nhìn vào, quả tìm được một bản chúc thư chính thức của ông già họ Lê. Trong chúc thư, người chủ tài sản dặn trao toàn bộ cho hai mẹ con thằng Phi, chứ không phải cho thằng rể.\n" +
                    "\n" +
                    "Ngày hôm sau, ông cho đòi anh chàng họ Bùi đến công đường báo phải nộp chúc thư văn khế về gia tài của bố vợ cho quan cứu xét. Tất cả giấy tờ y nộp quan cũng chỉ vẻn vẹn có hai mươi mốt chữ Hán đã nói ở trên. Quan đưa pho tượng, rút chúc thư chính thức cho hắn xem, và dõng dạc phán:\n" +
                    "\n" +
                    "Tất cả giấy tờ đều nói rằng ý muốn của người chết là để toàn bộ tài sản lại cho đứa con trai. Ngay cả mảnh giấy có dòng chữ mà anh giữ mấy lâu nay, cũng chỉ có nghĩa là để ruộng đất lại cho thằng Phi. Không tin ta giải cho anh nghe. Nghĩa của hai mươi mốt chữ đó là thế này:\n" +
                    "\n" +
                    "– “70 tuổi mới sinh thằng Phi, đó là con trai ta. Ruộng đất trao cho con. Còn rể là người ngoài, không được hiếm đoạt”. Thế mà anh dám cắt nghĩa bừa để chiếm lấy tất cả, không thương gì đến đứa em bé bỏng và mẹ của nó. Bây giờ ta lệnh cho anh phải trả lại ngay tất cả mọi thứ anh đã chiếm đoạt kể cả hoa lợi ruộng đất ấy trong mười tám năm nay!\n" +
                    "\n" +
                    "Anh chàng họ Bùi cứng họng khi nhẩm lại ý nghĩa mảnh di chúc, cuối cùng phải đành cúi đầu vâng lệnh. Còn hai mẹ con Phi hết lời cảm tạ quan huyện và từ đó bắt đầu một cuộc đời sung sướng.\n" +
                    "\n" +
                    "Nguồn: Truyện cổ tích Tổng hợp.", "", "");

    private static final Book book3 = new Book("Dân Gian Việt Nam", "Vụ án “Rắn giả lươn”",
            "Bùi Cầm Hồ là người làng Đỗ Liêu, xã Thiên Lộc, huyện Can Lộc, Hà Tỉnh được giữ chức Ngự sử trung thừa kiêm Tham chi chính sự dưới triều vua Lê Nhân Tông. Đó là một con người thông minh, trung thực và thẳng thắn. Việc thanh tra phá án của ông nổi tiếng là sáng suốt, có tình có lý được dân mến mộ và ủng hộ, sử sách ngợi ca. Với Bùi Cầm Hồ không một thế lực uy danh nào có thể làm sai lệch công việc, cản ngăn quyết định của ông.Một trong những vụ án mà ông xét xử là vụ “chinh phụ giết chồng”. Chuyện kể rằng, hồi đó ở ngoại ô kinh thành Thăng Long có một đôi vợ chồng nhà lái buôn nọ sống rất hoà thuận, êm đềm. Một ngày kia trước lúc chồng đi xa, người vợ liền đi mua lươn về để nấu cháo cho chồng ăn – món ăn mà người chồng rất thích. Nào ngờ vừa ăn xong lái buôn lăn ra chết không hề trăn trối được một lời. Lập tức chị ta bị chức sắc địa phương trối gô lại và dẫn lên Huyện đường xét xử. Chị đã bị ghép vào tội “Mưu sát chồng vì ngoại tình”. Người đàn bà đó dập đầu kêu oan nhưng sau đòn tra khảo cực hình không chịu đựng nổi nên đành phải nhận tội. Thế là án quyết xử hành hình bằng hình thức voi giày, chỉ chờ ngày thực hiện.\n" +
                    "\n" +
                    "Vụ án kinh động đó đã lan về Kinh Thành và đến tay Bùi Cầm Hồ. Ông suy nghĩ rất nhiều, phải chăng người đàn bà ấy đã vô tình mua nhầm loại rắn độc mình lươn lẫn trong đống lươn mà sinh ra tai hoạ. Bởi ông đã nhiều năm làm nghề khai khẩn đất hoang, và xuất thân từ vùng nông thôn nên hiểu rất rõ về loài rắn và lươn. Ông ngầm cho người ra cái chợ mà vợ lái buôn nọ đã tới và mua một mớ lươn về, chọn ra mấy con rắn độc mình lươn đem cho chó ăn, quả nhiên chó lăn đùng ra chết.\n" +
                    "\n" +
                    "Thế là người đàn bà góa bụa thương tâm ấy thoát khỏi án voi giày nghiệt ngã. Đâu đâu người ta cũng trầm trồ khen tài đức của quan ngự sử Bùi Cầm Hồ.\n" +
                    "\n" +
                    "Nguồn: Truyện cổ tích Tổng hợp.", "", "");

}
