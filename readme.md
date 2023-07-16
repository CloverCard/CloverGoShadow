<!-----

Yay, no errors, warnings, or alerts!

Conversion time: 0.684 seconds.


Using this HTML file:

1. Paste this output into your source file.
2. See the notes and action items below regarding this conversion run.
3. Check the rendered output (headings, lists, code blocks, tables) for proper
   formatting and use a linkchecker before you publish this page.

Conversion notes:

* Docs to Markdown version 1.0β34
* Thu Jul 13 2023 12:35:14 GMT-0700 (PDT)
* Source doc: CloverGoShadow_Documentation
* Tables are currently converted to HTML tables.
----->


<h1>CloverGoShadow</h1>


<h2>Introduction</h2>


<p>
Shadow Pokemon are, according to the lore, Pokemon who have been artificially been made more powerful through the insidious practices of villainous teams such as Team Rocket or Cipher, and have been a popular gimmick found within several non-mainline Pokemon games such as Pokemon Colosseum, Pokemon XD Gale of Darkness, and more recently Pokemon Go.
</p>
<p>
This mod seeks to reinterpret the Pokemon Go implementation into a Pixelmon friendly format which is highly configurable for singleplayer or multiplayer servers.
</p>
<img src="https://cdn.discordapp.com/attachments/319251161343328256/1129137847732015114/image.png"/>
<hr>
<h2>Features</h2>


<ul>

<li>Introduces Shadow Pokemon into the world of Pixelmon Shadow Pokemon can spawn in the wild or can spawn after beating a suspicious trainer (Don’t worry about missing them, you’ll be notified in the chat). They also can be encountered through Shadow Wish Pieces which summon a shadow raid pokemon (gained through purification or leveling). The spawn rates of all three can be set in the config file.

<li>Configurable Spawn Pokemon: Pokemon species can be added to a black list in the config to prevent them from spawning as wild pokemon or as wish pieces. Additionally by default, only default forms will spawn as wishpieces. If you want to configure a form for wish pieces, it will need to be placed within its whitelist.

<li>Shadow Pokemon gain a Shadow Boost. By default it is 1.2x Base Attack and Special Attack, Base 0.8x Defense and Special Defense. These stats can be modified in the config file and can be changed to alter all 6 stats.

<li>Shadow Pokemon need your help! Although powerful, due to their trauma, they face difficulties. 
<ul>
 
<li>Upon being caught, a Shadow Pokemon is reverted back to level one including their moveset. But all empty slots are filled with random egg moves.
 
<li>Shadow Pokemon face severe experience and effort values penalties. (0.25 EXP and 0.5 EVs rounded down). These modifiers can be altered in the config.
</li> 
</ul>

<li>Upon reaching level 50, a Shadow Pokemon can be purified. Purifying a Shadow Pokemon turns them back to a normal pokemon, changes 1-3 of their ivs to 31, modifies their experience and ev gain rate (1.2 EXP, 1.5 EVs rounded down), and has the chance to reward a non-legendary shadow wish piece (1-4 stars).

<li>Shadow and Purified Pokemon gain special particle effects. These effects can be toggled on and off by removing/enabling the corresponding ribbon.

<li>Creates a research leveling system which resets after level 50. Players can gain experience from defeating shadow pokemon, shadow trainers, and shadow raids. They can get additional experience for catching shadow pokemon and purifying them.  
<ul>
 
<li>For every 10 levels, you can gain a legendary pokemon wishing piece (5 stars).
 
<li>The base level experience and per level increase factor can be manipulated in the config file.
</li> 
</ul>

<li>Resourcepack-able Language Support. By default this is set to false in the config, but if you enable it to true, you can convert the messages and titles in this mod to your ideal language. A default en-us.json can be found in the assets folder of the mod to help you configure for your respective language.
</li>
</ul>
<hr>
<h2>Commands</h2>



<table>
  <tr>
   <td>/clovergoshadow purify [slot]
   </td>
   <td>Purifies a Shadow Pokemon that is at least level 50.
   </td>
  </tr>
   <td>/clovergoshadow purifytarget [player] [slot]
   </td>
   <td>Purifies the pokemon in the slot provided (if applicable) of the target player.
   </td>
  </tr>
  <tr>
   <td>/clovergoshadow stats
   </td>
   <td>Tells you your current level and the amount of exp you need to level up.
   </td>
  </tr>
   <tr>
   <td>/clovergoshadow giveshadow [player] [specs]
   </td>
   <td>Gives the specified player the shadow form of the pokemon specified in the specs.
   </td>
  </tr>
   <tr>
   <td>/clovergoshadow spawnshadow [specs]
   </td>
   <td>Spawns the shadow pokemon provided in specs on the command user.
   </td>
  </tr>
</table>


<hr>
<h2>Config</h2>



<table>
  <tr>
   <td>Field
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>shadowSpawnPercent
   </td>
   <td>The odds that a pokemon spawning in the wild will change into a shadow pokemon.
   </td>
  </tr>
  <tr>
   <td>shadowTrainerPercent
   </td>
   <td>The odds that a trainer spawning in the wild will change into a suspicious trainer.
   </td>
  </tr>
  <tr>
   <td>shadowRaidPiece
   </td>
   <td>The odds that that purifying a pokemon will result in gaining a shadow wishing piece.
   </td>
  </tr>
  <tr>
   <td>shadowExpGainMultiplier
   </td>
   <td>The rate at which shadow pokemon gain experience.
   </td>
  </tr>
  <tr>
   <td>purifiedExpGainMultiplier
   </td>
   <td>The rate at which purified pokemon gain experience
   </td>
  </tr>
  <tr>
   <td>shadowEvGainMultiplier
   </td>
   <td>The rate at which shadow pokemon gain effort values.
   </td>
  </tr>
  <tr>
   <td>purifiedEvGainMultiplier
   </td>
   <td>The rate at which purified pokemon gain effort values.
   </td>
  </tr>
  <tr>
   <td>shadowBoostAttack
   </td>
   <td>Shadow Boost’s Attack modifier
   </td>
  </tr>
  <tr>
   <td>shadowBoostDefense
   </td>
   <td>Shadow Boost’s Defense modifier
   </td>
  </tr>
  <tr>
   <td>shadowBoostSpecialAttack
   </td>
   <td>Shadow Boost’s Special Attack modifier
   </td>
  </tr>
  <tr>
   <td>shadowBoostSpecialDefense
   </td>
   <td>Shadow Boost’s Special Defense modifier
   </td>
  </tr>
  <tr>
   <td>shadowBoostSpeed
   </td>
   <td>Shadow Boost’s Speed modifier
   </td>
  </tr>
  <tr>
   <td>useTranslatables
   </td>
   <td>Toggles whether to use resourcepack lang files or not.
   </td>
  </tr>
  <tr>
   <td>shadowBlackList
   </td>
   <td>Pokemon species listed here will not spawn as wild pokemon or wish pieces
   </td>
  </tr>
  <tr>
   <td>baseExp
   </td>
   <td>The exp required for level 1 and the base used for future levels.
   </td>
  </tr>
  <tr>
   <td>expDifPerLevel
   </td>
   <td>The amount of additional experience required to level up. I.E When the level dif is 10, then Level 0 -> Level 1 is 100 -> 110.
   </td>
  </tr>
  <tr>
   <td>expTrainerVictory
   </td>
   <td>The amount of exp gained for defeating a suspicious trainer.
   </td>
  </tr>
  <tr>
   <td>expShadowPokemonCapture
   </td>
   <td>The amount of exp gained for catching a suspicious trainer’s pokemon or a wild shadow pokemon.
   </td>
  </tr>
  <tr>
   <td>expShadowPokemonRaidCapture
   </td>
   <td>The amount of exp gained for catching a shadow 
   </td>
  </tr>
  <tr>
   <td>expShadowWildPokemonVictory
   </td>
   <td>The amount of exp gained for defeating a wild shadow pokemon
   </td>
  </tr>
  <tr>
   <td>expPurifyShadowPokemon
   </td>
   <td>The amount of exp gained for purifying a shadow pokemon
   </td>
  </tr>
  <tr>
   <td>expShadowRaidPokemonVictory
   </td>
   <td>The amount of exp gained for defeating a shadow raid pokemon.
   </td>
  </tr>
  <tr>
   <td>shadowFormWhiteList
   </td>
   <td>The forms of a pokemon that can spawn as wishing pieces.
   </td>
  </tr>
</table>





<pre class="prettyprint">{
  "shadowSpawnPercent": 0.0125,
  "shadowTrainerPercent": 0.1,
  "shadowRaidPiece": 100.0,
  "shadowExpGainMultiplier": 0.25,
  "purifiedExpGainMultiplier": 1.2,
  "shadowEvGainMultiplier": 0.5,
  "purifiedEvGainMultiplier": 1.5,
  "shadowBoostAttack": 1.2,
  "shadowBoostDefense": 0.8,
  "shadowBoostSpecialAttack": 1.2,
  "shadowBoostSpecialDefense": 0.8,
  "shadowBoostSpeed": 1.0,
  "useTranslatables": false,
  "shadowBlackList": [],
  "baseExp": 100,
  "expDifPerLevel": 10,
  "expTrainerVictory": 10,
  "expShadowPokemonCapture": 5,
  "expPurifyShadowPokemon": 50,
  "expShadowPokemonRaidCapture": 25,
  "expShadowWildPokemonVictory": 5,
  "expShadowRaidPokemonVictory": 15,
  "shadowFormWhiteList": []
} </pre>

