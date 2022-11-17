package com.gaberrockking.cyoa.generator.pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Generate {
    
    public static void main (String[] args) throws IOException {

        System.out.println("\tI, Arceus, have decided that all the humans on your world are about to become pokemon. No, you don't have any choice in the matter. You'll all be transformed based on a perfectly fair, deterministic process I conceived of while taking a dump. In my infinite mercy, I'm allowing you a glimpse of what pokemon you'll become. Enter your information below to find out what pokemon you'll be transforming into.");
        System.out.println("\tOh for the love of ME, stop whining about the \"no hands\" thing. Why are humans so obsessed with those? I don't have any and I'm totally fine.");
        System.out.println("\tAh, whatever. If it'll shut you all up, I'll let the lot of you optionally transform into gijinka or furry versions of the pokemon you'll be assigned to. But no more compromises!");
        System.out.println();

        // Get player data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // get name
        System.out.println("\tEnter your full, legal name (in simplified latin characters):");
        String name = reader.readLine();
        int hashCode = name.hashCode();

        // get region
        System.out.println("\tNext, you'll be answering a number of questions about yourself. Respond with \"y\" if the answer is \"yes\".");
        System.out.println("\tAre you from (or hold the citizenship of)...");
        System.out.println("* Japan?");
        boolean japan = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("* America?");
        boolean america = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("* France?");
        boolean france = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("* Any nation in the British Isles?");
        boolean britain = "y".equalsIgnoreCase(reader.readLine());

        // Misc questions
        System.out.println("\tAre you a teenager? (13-19 years of age)");
        boolean teenager = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tHave you ever received a senior discount?");
        boolean senior = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tDo you have a child?");
        boolean parent = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tDo you have a black belt in any martial art?");
        boolean combat = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tHave you previously served in a military or police force?");
        combat = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tHave you ever spent any amount of time in jail or prison?");
        combat = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tDo you fluently speak at least two languages?");
        boolean xenophile = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tHave you been to at least five different countries?");
        xenophile = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tHave you ever created a CYOA before?");
        boolean cyoaCreator = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tAre you a furry?");
        boolean furry = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tHave you ever completed a Nuzlocke and written a story about it?");
        boolean meme = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tDid you participate in the original \"Twitch Plays Pokemon\"?");
        meme = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tHave you ever reposted a pokemon-themed meme or CYOA and recieved more upvotes than the original post?");
        meme = "y".equalsIgnoreCase(reader.readLine());
        System.out.println("\tHave either of your parents ever worked for Nintendo?");
        meme = "y".equalsIgnoreCase(reader.readLine());

        System.out.println("\tDo you hold an Oscar, Olympic medal, Nobel prize, or membership in the Rock 'n Roll Hall of Fame or the forbes \"Most Powerful People\" list?");
        boolean celebrity = "y".equalsIgnoreCase(reader.readLine());

        reader.close();

        // Read CSV
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		CSVParser parser = new CSVParser(new FileReader("Pokemon_Characteristics_Spreadsheet.csv"), format);
        List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		for(CSVRecord record : parser){
            if (record.get("number").isBlank()) break;
			Pokemon pokemon = new Pokemon();
            pokemonList.add(pokemon);
			pokemon.number = Integer.parseInt(record.get("number"));
            pokemon.name = record.get("name");
            pokemon.baby = !record.get("baby").isBlank();
            pokemon.unevolved = !record.get("unevolved").isBlank();
            pokemon.midStage = !record.get("midStage").isBlank();
            pokemon.fullyEvolved = !record.get("fullyEvolved").isBlank();
            pokemon.noEvolutions = !record.get("noEvolutions").isBlank();
            pokemon.multipleForms = !record.get("multipleForms").isBlank();
            pokemon.regionalForms = !record.get("regionalForms").isBlank();
            pokemon.mega = !record.get("mega").isBlank();
            pokemon.gigantamax = !record.get("gigantamax").isBlank();
            pokemon.psuedolegendary = !record.get("psuedolegendary").isBlank();
            pokemon.legendary = !record.get("legendary").isBlank();
            pokemon.mythical = !record.get("mythical").isBlank();
            pokemon.japan = !record.get("japan").isBlank();
            pokemon.america = !record.get("america").isBlank();
            pokemon.france = !record.get("france").isBlank();
            pokemon.britain = !record.get("britain").isBlank();
            pokemon.furry = !record.get("furry").isBlank();
            pokemon.meme = !record.get("meme").isBlank();

		}
		parser.close();

        List<Pokemon> mainList = pokemonList.stream().filter(pokemon -> isOrdinaryPokemon(pokemon) && !isRarePokemon(pokemon)).collect(Collectors.toList());
 
        // regional lists
        List<Pokemon> japanList = pokemonList.stream().filter(pokemon -> pokemon.japan && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> americaList = pokemonList.stream().filter(pokemon -> pokemon.america && !isRarePokemon(pokemon)).collect(Collectors.toList());
        // Because the France and Britain pokemon tables have the same size, and both feature the first 9 pokemon from the Kanto Dex, it's "relatively" common to get the same option from both tables. Arceus thinks this is hilarious.
        List<Pokemon> franceList = pokemonList.stream().filter(pokemon -> pokemon.france && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> britainList = pokemonList.stream().filter(pokemon -> pokemon.britain && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> restOfTheWorldList = pokemonList.stream().filter(pokemon -> !pokemon.regionalForms && !pokemon.mega && !pokemon.gigantamax && !isRarePokemon(pokemon)).collect(Collectors.toList());
        // characteristic lists
        List<Pokemon> teenagerList = pokemonList.stream().filter(pokemon -> pokemon.midStage && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> seniorList = pokemonList.stream().filter(pokemon -> pokemon.fullyEvolved && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> parentList = pokemonList.stream().filter(pokemon -> pokemon.baby && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> combatList = pokemonList.stream().filter(pokemon -> (pokemon.mega || pokemon.gigantamax || pokemon.psuedolegendary) && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> xenophileList = pokemonList.stream().filter(pokemon -> (pokemon.multipleForms || pokemon.regionalForms) && !isRarePokemon(pokemon)).collect(Collectors.toList());
        List<Pokemon> cyoaCreatorList = pokemonList.stream().filter(pokemon -> pokemon.baby || pokemon.legendary).collect(Collectors.toList());
        List<Pokemon> furryList = pokemonList.stream().filter(pokemon -> pokemon.furry).collect(Collectors.toList());
        List<Pokemon> memeList = pokemonList.stream().filter(pokemon -> pokemon.meme).collect(Collectors.toList());
        List<Pokemon> celebrityList = pokemonList.stream().filter(pokemon -> isRarePokemon(pokemon)).collect(Collectors.toList());

        System.out.println();
        System.out.println("Debug info:" + hashCode);
        System.out.println();

        System.out.println("\tWhile the process for selecting which pokemon you'll become is deterministic, you get to do part of that determining yourself. You're eligible to become a number of different pokemon for different reasons. Select which one you wish to be the most. Note that some your options have benefits or preconditions associated with them. Those benefits or preconditions only apply if you choose the pokemon supplied by that option to base your transformation off of.");

        printFormattedPokemonOption(mainList, hashCode, "The pokemon you are most suited to becoming. If you decide to become this pokemon, you gain an additional Customization Point.");

        if (japan || america || france || britain) {
            if (japan) {
                printFormattedPokemonOption(japanList, hashCode, "An option because of your Japanese heritage or nationality. You may choose to become or later evolve into a Hisuian variant of that pokemon, if applicable, for free.");
            }
            if (america) {
                printFormattedPokemonOption(americaList, hashCode, "An option because of your American heritage or nationality. You may choose to become or later evolve into an Alolan variant of that pokemon, if applicable, for free.");
            }
            if (france) {
                printFormattedPokemonOption(franceList, hashCode, "An option because of your French heritage or nationality. You gain the capacity for mega evolution for free.");
            }
            if (britain) {
                printFormattedPokemonOption(britainList, hashCode, "An option because of your heritage or nationality from the British Isles. You gain the capacity to gigantamax for free.");
            }
        }

        if (!japan && !america && !france && !britain) {
                printFormattedPokemonOption(restOfTheWorldList, hashCode, "An option because of your heritage of nationality.");
        }

        if (teenager) {
            printFormattedPokemonOption(teenagerList, hashCode, "Enabled by the critical stage in your life you find yourself. You cannot use a Customization Point to become a different evolutionary stage of this pokemon.");
        }

        if (senior) {
            printFormattedPokemonOption(seniorList, hashCode, "Enabled by your age and (hopefully) maturity. You cannot use a Customization Point to become a different evolutionary stage of this pokemon.");
        }

        if (parent) {
            printFormattedPokemonOption(parentList, hashCode, "Enabled by your status as a parent. You become any evolution of this pokemon for free but CANNOT stay as this pokemon.");
        }

        if (combat) {
            printFormattedPokemonOption(combatList, hashCode, "Your right, as someone acquainted with violence.");
        }

        if (xenophile) {
            printFormattedPokemonOption(xenophileList, hashCode, "An option as a result of your xenophilia. You may become or enable evolution into a regional variant of this pokemon for free.");
        }

        if (cyoaCreator) {
            printFormattedPokemonOption(cyoaCreatorList, hashCode, "An option to reward you for being a CYOA creator.");
        }

        if (furry) {
            printFormattedPokemonOption(furryList, hashCode, "Enabled by you being a furry. Your default level of anthropomorphism cannot be \"Gijinka,\"");
        }

        if (meme) {
            printFormattedPokemonOption(memeList, hashCode, "Enabled by you being a memelord. Lose two Customization Points.");
        }

        if (celebrity) {
            printFormattedPokemonOption(celebrityList, hashCode, "Enabled by you being a celebrity. Lose two Customization Points.");
        }

        System.out.println();
        System.out.println("You additionally have 3 Customization Points to spend to further customize the pokemon you'll become. Each option costs 1 Customization point, unless otherwise specified. Each optino can only be bought once. Some options are mandatory.");
        System.out.println("* {FREE, MANDATORY} Choose your level of anthropomorphism from among [Gijinka, Anthropomorphic, Full Pokemon]. Your form will always feel \"natural\" and you'll always be able to speak.");
        System.out.println("* {FREE, MANDATORY (if applicable)} If you are a pokemon that comes in multiple permanent forms like Vivillion (excluding regional forms), select your default form now. (If you are a pokemon that has multiple temporary forms, you'll be able to switch between them as your species dictates. For example, Castaform mirrors the weather.)");
        System.out.println("* {FREE, MANDATORY (if applicable)} If you are a pokemon species that can have either of several non-hidden abilities, select which one you have.");
        System.out.println("* Select another ability or hidden ability your pokemon species has access to. You can swap between posessing the ability you chose in the previous choice to posessing this second ability once per day.");
        System.out.println("* Select a another level of anthropomorphism. You can spend up to thirty minutes a day in that form.");
        System.out.println("* If you are a pokemon that comes in multiple permanent forms like Vivillion (excluding regional forms), select another permanent form you can spend up to 30 minutes per day in.");
        System.out.println("* If you are a pokemon that comes in or can evolve into a regional form, you become the regional variant of the pokemon and/or will now evolve into the specified regional variant on evolution.");
        System.out.println("* You become capable of mega evolution once (if applicable) you reach an evolutionary stage that can mega evolve.");
        System.out.println("* You become capable of gigantamaxing once (if applicable) you reach an evolutionary stage capable of gigantamaxing.");
        System.out.println("* You become any other evolutionary stage or alternate evolution from the same evolutionary line as the pokemon you selected. (You cannot choose to become a regional form of your pokemon unless you are already a preceding regional form.)");
        System.out.println("* Select another pokemon from your options. You maintain the powers and prerequisites of your first selected pokemon, but may hybridize your appearance to any sensible degree with the second selected pokemon without changing your capabilities. (So, for example, you can't get rid of wings, or add gills.)");
        System.out.println("* Find the national pokedex (PD#) number of your selected pokemon. You may spend up to thirty minutes a day with your appearance changed to match the pokemon with pokedex numbers PD#-5 and/or the pokemon with pokedex number PD#+5. During this time you'll possess the physical body of this other pokemon (adjusted to your current level of anthropomorphism,) but cannot use any special abilities from either your actual pokemon type or the alternate form you\'re in.");
        System.out.println("* {2 CP} Your color scheme now matches the shiny color scheme for your species.");

        System.out.println();
        System.out.println("Closing notes:");
        System.out.println("\tIf you are a pokemon that is 100% either sex, you become that sex if you were not already. If you become a pokemon with no recorded sex, you mantain your current sex.");
        System.out.println("\tItems from the pokemon games, such as evolutionary stones, will begin to appear in the real world.");
    }

    static boolean isOrdinaryPokemon(Pokemon pokemon) {
        return pokemon.japan || pokemon.america || pokemon.france || pokemon.britain;
    }

    static boolean isRarePokemon(Pokemon pokemon) {
        return pokemon.legendary || pokemon.mythical;
    }

    static Pokemon generatePokemonFromList(List<Pokemon> specialList, int hashCode) {
        return specialList.get(hashCode % specialList.size());
    }

    static void printFormattedPokemonOption(List<Pokemon> specialList, int hashCode, String explanation) {
        System.out.println("[" + generatePokemonFromList(specialList, hashCode).name + "] - " + explanation);
    }
}
