package com.susie.stanley.interview.typeinfo.pets.creator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.susie.stanley.interview.typeinfo.pets.Cat;
import com.susie.stanley.interview.typeinfo.pets.Cymric;
import com.susie.stanley.interview.typeinfo.pets.Dog;
import com.susie.stanley.interview.typeinfo.pets.EgyptianMau;
import com.susie.stanley.interview.typeinfo.pets.Hamster;
import com.susie.stanley.interview.typeinfo.pets.Manx;
import com.susie.stanley.interview.typeinfo.pets.Mouse;
import com.susie.stanley.interview.typeinfo.pets.Mutt;
import com.susie.stanley.interview.typeinfo.pets.Pet;
import com.susie.stanley.interview.typeinfo.pets.Pug;
import com.susie.stanley.interview.typeinfo.pets.Rat;
import com.susie.stanley.interview.typeinfo.pets.Rodent;

public class LiteralPetCreator extends PetCreator {
	
	public static final List<Class<? extends Pet>> allTypes = 
			Collections.unmodifiableList(Arrays.asList(Pet.class, 
					Dog.class, Cat.class,  Rodent.class,Mutt.class, 
					Pug.class, EgyptianMau.class, Manx.class,Cymric.class, 
					Rat.class, Mouse.class,Hamster.class));

	@Override
	public List<Class<? extends Pet>> getTypes() {
		return allTypes;
	}

	public static void main(String[] args) {
		System.out.println(allTypes);
	}

}
