package lotto.utils;

import java.util.List;

import lotto.domain.LottoNumber;

public interface LottoNumberGenerator {
	List<LottoNumber> generate();
}