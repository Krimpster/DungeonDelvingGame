package org.example.interfaces;

import org.example.objects.player.PlayerCharacter;

public interface IPlayerSkillCommand {
    double execute(PlayerCharacter playerCharacter, String input);
}
