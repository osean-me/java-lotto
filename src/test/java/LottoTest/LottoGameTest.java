package LottoTest;

import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class LottoGameTest {
    @Test
    void Given_StringNumber_When_New_Then_InstanceCreated() {
        //given
        String number = "1, 2, 3, 4, 5, 6";

        //when
        LottoGame lottoGame = new LottoGame(number);

        //then
        assertThat(lottoGame).isEqualTo(new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "6:true", "7:false"}, delimiter = ':')
    @DisplayName("contains 테스트")
    void When_Contains(int number, boolean expected) {
        LottoGame lottoGame = new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lottoGame.contains(LottoNumber.create(number))).isEqualTo(expected);
    }

    @Test
    @DisplayName("numbers()의 반환값이 Immutable인지 테스트")
    void When_Number_Then_ImmutableObject() {
        LottoGame lottoGame = new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<LottoNumber> lottoNumbers = lottoGame.numbers();

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> lottoNumbers.add(LottoNumber.create(10)));
    }
    
    @ParameterizedTest
    @MethodSource("provideCountOfMatchTestInput")
    @DisplayName("countOfMatch 테스트")
    void When_countOfMatch(List<Integer> given, int expected) {
        LottoGame lottoGame = new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6));

        int countOfMatch = lottoGame.countOfMatch(new LottoGame(given));

        assertThat(countOfMatch).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCountOfMatchTestInput() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 8, 7), 4),
                Arguments.of(Arrays.asList(1, 2, 3, 9, 8, 7), 3),
                Arguments.of(Arrays.asList(1, 2, 10, 9, 8, 7), 2),
                Arguments.of(Arrays.asList(1, 11, 10, 9, 8, 7), 1),
                Arguments.of(Arrays.asList(12, 11, 10, 9, 8, 7), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInput")
    @DisplayName("잘못된 로또 번호면 Exception 테스트")
    void Given_InvalidNumbers_When_New_Then_Exception(List<Integer> invalidNumbers) {
        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> new LottoGame(invalidNumbers));
    }

    private static Stream<Arguments> provideInvalidInput() {
        return Stream.of(
                Arguments.of(Arrays.asList(0, 2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(46, 2, 3, 4, 5, 6))
        );
    }
}