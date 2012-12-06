package pasoos.view;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AIPlayerStateTest.class,
        GammonBuildDirectorTest.class,
        HotgammonPieceFactoryTest.class,
        GameEventDecoratorTest.class,
        //GerryPlayerTest.class,
        HotgammonPositioningStrategyTest.class})
public class AllViewTests {

}