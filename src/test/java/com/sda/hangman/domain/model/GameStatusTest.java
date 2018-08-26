package com.sda.hangman.domain.model;

import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;
import org.junit.Assert;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Enclosed.class)
public class GameStatusTest {

    public static class GameStatusHelperTest {

        @Test
        public void should_return_array_with_null_valuies_for_particular_phrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();
            //when
            Character[] phraseState = gameStatusHelper.preparePhraseState("Anna");
            //then
            assertThat(phraseState).allMatch((e) -> e == null);


        }

        @Test
        public void should_return_array_with_null_and_special_characters_separating_words_in_phrase() {

            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            Character[] phraseState = gameStatusHelper.preparePhraseState("Ala ma-kota");

            assertThat(phraseState).containsOnly(null, ' ', '-');

            Assert.assertEquals((Character) ' ', phraseState[3]);
            Assert.assertEquals((Character) '-', phraseState[6]);

        }

    }
}