package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Color;
import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.RED),
            new User(6, "pashka", "Pavel Mavrin", Color.BLUE),
            new User(9, "geranazarov555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.GREEN),
            new User(15, "cannon147", "Erofey Bashunov", Color.BLUE)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, 1, "Codeforces Round #675 (Div. 2)", "Трям, Codeforces!\n" +
                    "\n" +
                    "andersen\n" +
                    "\n" +
                    "Возможно, вы ждете от нас анонс финала чемпионата БГУИР, но пока мы только рады пригласить вас на Codeforces Round #675 (Div. 2), который пройдет воскресенье, 4 октября 2020 г. в 19:05. Этот раунд будет рейтинговым для участников, чей рейтинг ниже 2100.\n" +
                    "\n" +
                    "Задачи для вас кроме меня готовили andrew, hloya_ygrt, AleXman111 и Vladik. Мы думаем, что подготовили хорошие задачи на Andersen Programming Contest 2020. Квалификация. Потом мы отобрали лучшие из них для этого раунда.\n" +
                    "\n" +
                    "Компания Andersen уже второй год проводит соревнование, которое в первую очередь предназначено для поддержки студентов региональных ВУЗов Беларуси и Украины (с этого года).\n" +
                    "\n" +
                    "В первую очередь благодарим MikeMirzayanov и всех, кто причастен к развитию платформ Codeforces и Polygon. Не меньшая благодарность KAN за координацию — благодаря ему вы сможете понять наши задачи. А также всем нашим тренерам и родителям, которые научили нас делать все то, что мы умеем.\n" +
                    "\n" +
                    "Разбалловка обещает быть такой: 500 — 750 — 1000 — 1500 — 2000 — 2750.\n" +
                    "\n" +
                    "Всем удачи и чистого кода!"),
            new Post(2, 6, "Codeforces Round #674 (Div. 3)", "Привет! В понедельник, 28 сентября 2020 г. в 11:05 начнётся Codeforces Round #674 (Div. 3) — очередной Codeforces раунд для третьего дивизиона. В этом раунде будет 6 или 7 задач (или 8), которые подобраны по сложности так, чтобы составить интересное соревнование для участников с рейтингами до 1600. Однако все желающие, чей рейтинг 1600 и выше могут зарегистрироваться на раунд вне конкурса.\n" +
                    "\n" +
                    "Раунд в основном состоит из задач первого этапа Всероссийской олимпиады школьников в Саратове и будет проведен во время реального соревнования. Задачи были придуманы и приготовлены Иваном BledDest Андросовым, Александром fcspartakm Фроловым и мной.\n" +
                    "\n" +
                    "Раунд пройдет по правилам образовательных раундов. Таким образом, во время раунда задачи будут тестироваться на предварительных тестах, а после раунда будет 12-ти часовая фаза открытых взломов. Я постарался сделать приличные тесты — так же как и вы буду расстроен, если у многих попадают решения после окончания контеста.\n" +
                    "\n" +
                    "Вам будет предложено 6 или 7 (или 8) задач и 2 часа на их решение."),
            new Post(4, 9, "Codeforces Round #673",  "Ас-саляму алейкум, Codeforces! (Мир вам, Codeforces!)\n" +
                    "\n" +
                    "Мы рады пригласить вас на Codeforces Round #673 (Div. 1) и Codeforces Round #673 (Div. 2), который пройдет в воскресенье, 27 сентября 2020 г. в 18:05 (Обратите внимание, что время позже, чем обычно). Раунд будет рейтинговым для обоих дивизионов. Участникам обоих дивизионов будет предложено шесть задач и два часа на их решение.\n" +
                    "\n" +
                    "Задачи были придуманы RedDreamer, BThero и мною DimmyT.\n" +
                    "\n" +
                    "Мы очень благодарны следующим людям, которые помогли в подготовке раунда:\n" +
                    "\n" +
                    "Огромное спасибо BledDest за блестящую координацию нашего раунда.\n" +
                    "\n" +
                    "Cпасибо adedalic, kdjonty31, PM11, namanbansal013, RockyB, postscript, saurabhshadow, morzer, Mohamed.Sobhy, growup974, hugopm, Tlatoani, dorijanlendvaj и Roms за тестирование задач, советы и честные отзывы!\n" +
                    "\n" +
                    "А также спасибо MikeMirzayanov за отличные системы Codeforces и Polygon!\n" +
                    "\n" +
                    "Это наш первый раунд, надеемся, что задачи будут интересными и что раунд пройдет без проблем"),
            new Post(5, 1, "Codeforces Round #1000",  "Привет, Codeforces! \n" +
                    "\n" +
                    "Мы рады пригласить вас на Codeforces Round #1000 (Div. 1) и Codeforces Round #673 (Div. 2), который пройдет в воскресенье, 27 сентября 2020 г. в 18:05 (Обратите внимание, что время позже, чем обычно). Раунд будет рейтинговым для обоих дивизионов. Участникам обоих дивизионов будет предложено шесть задач и два часа на их решение.\n" +
                    "\n" +
                    "Задачи были придуманы RedDreamer, BThero и мною DimmyT.\n" +
                    "\n" +
                    "Мы очень благодарны следующим людям, которые помогли в подготовке раунда:\n" +
                    "\n" +
                    "Огромное спасибо BledDest за блестящую координацию нашего раунда.\n" +
                    "\n" +
                    "Cпасибо adedalic, kdjonty31, PM11, namanbansal013, RockyB, postscript, saurabhshadow, morzer, Mohamed.Sobhy, growup974, hugopm, Tlatoani, dorijanlendvaj и Roms за тестирование задач, советы и честные отзывы!\n" +
                    "\n" +
                    "А также спасибо MikeMirzayanov за отличные системы Codeforces и Polygon!\n" +
                    "\n" +
                    "Это наш первый раунд, надеемся, что задачи будут интересными и что раунд пройдет без проблем"),
            new Post(7, 15, "Codeforces Round #676 (Div. 2)", "Hi Codeforces!\n" +
                    "\n" +
                    "stefdasca, koala_bear00 and I are very excited to announce our first contest Codeforces Round #676, which will take place воскресенье, 18 октября 2020 г. в 18:35. The round will be rated for participants with rating up to 2099.\n" +
                    "\n" +
                    "The tasks were written by me with help from stefdasca and koala_bear00 and we hope we compiled a very interesting contest with memorable tasks :)\n" +
                    "\n" +
                    "Special thanks to:\n" +
                    "\n" +
                    "antontrygubO_o for coordinating our round and pushing us to come up with more and more interesting tasks.\n" +
                    "\n" +
                    "dorijanlendvaj, kclee2172, Devil, * thenymphsofdelphi, jainbot27, Stelutzu, proggerkz, SleepyShashwat, bhaskarjoshi2001, AmShZ, Osama_Alkhodairy, raresdanut, katsurap_, A_N_D_Y, Usu and Miyukine for testing the round and providing useful feedback.\n" +
                    "\n" +
                    "MikeMirzayanov for awesome Codeforces and Polygon platforms. Thanks!\n" +
                    "\n" +
                    "You will be given 2 hours to solve 5 problems, good luck everyone and have fun!\n" +
                    "\n" +
                    "UPD 1: After the round you can watch videos explaining the solutions to the tasks on stefdasca's Youtube channel.")
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
