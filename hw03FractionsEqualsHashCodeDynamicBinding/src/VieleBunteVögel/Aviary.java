package VieleBunteVögel;

/**
 * A Class, showing the different abilities of birds in the aviary
 *
 * @author Kevin Trebing
 */
public class Aviary {

   public static void main(String[] args) {
      Dodo dodo = new Dodo();
      // the attribute ability is not overwritten by the subclass, it is just shadowed. Therefore by explicitly converting
      // to the super class the attribute of the superclass will be again accessible.
      System.out.println("1: ((Bird)dodo).ability           : " + ((Bird) dodo).ability);
      // attributes of the super class are shadowed by attributes from the subclass, thus the ability of the subclass is chosen.
      System.out.println("2: dodo.ability                   : " + dodo.ability);
      // getAbility of Dodo calls the getAbility() of its superclass which returns the superclass' ability attribute (again, attributes are not overwritten).
      System.out.println("3: dodo.getAbility()              : " + dodo.getAbility());

      Parrot parrot = new Parrot();
      // nothing magical here, Parrot.allAbilities() calls the allAbilities() of its super class and appends its own ability to the resulting string.
      System.out.println("4: parrot.allAbilities()            : " + parrot.allAbilities());
      // Parrots attribute ability shadows the super class' ability -> Parrots ability is returned.
      System.out.println("5: parrot.ability                 : " + parrot.ability);

      Bird carsten = new Dodo();
      // attributes are statically bound to the Class/ their type. Therefore carsten is here treated
      // as a Bird even though he is a Dodo. Attributes are shadowed, not overwritten.
      System.out.println("6: carsten.ability                : " + carsten.ability);
      // carsten is already a bird, meaning that the cast is redundant. Anyway, the Dodo subclass overrides the method of Bird,
      // therefore the method is no longer callable even when casting to a Bird.
      System.out.println("7: ((Bird)carsten).allAbilities() : " + ((Bird) carsten).allAbilities());

      Bird einstein = parrot;
      // after we casted the Parrot to a Bird, .allAbilities will still be called from Parrot as it was overridden in Parrot.
      System.out.println("8: einstein.allAbilities()        : " + einstein.allAbilities());
      // .getAbility() was not overridden in the Parrot class therefore the method will be directly called from the super class
      // and thus the attribute ability of Bird will be called. (attributes are not overridden!)
      System.out.println("9: einstein.getAbility()          : " + einstein.getAbility());
      // if the Bird is casted back to a Parrot, the ability of Parrot will again shadow the attribute of the super class
      // and the ability of the Parrot will be printed.
      System.out.println("10: ((Parrot)einstein).ability    : " + ((Parrot) einstein).ability);

      // this will throw a runtime cast error because we cannot cast a parrot to a Dodo, even though we tricked
      // the compiler by falsely trusting us.
      dodo = (Dodo)(Bird)parrot;

   }
}
