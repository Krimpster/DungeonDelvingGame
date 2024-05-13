package org.example.interfaces;

import org.example.objects.monsters.Monster;

public interface IFactoryMonster {
    Monster createMonster(String name, int hp, int baseAttack, int baseDefense, int baseFocusPoints, int focusPoints,
                          int focusPointsPerTurn, int skillDamageVariance, String monsterSkill, String bossSkill, int expReward);
}
