/*
 * 
 * Copyright (C) 2010, University of Aberdeen
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplenlg.test.syntax;

import java.util.Arrays; // needed for Section 14

import junit.framework.Assert;

import org.junit.Test;

import simplenlg.features.Feature;
import simplenlg.features.InterrogativeType;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

/**
 * Tests from tutorial
 * <hr>
 * 
 * <p>
 * Copyright (C) 2011, University of Aberdeen
 * </p>
 * 
 * <p>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * </p>
 * 
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * </p>
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License in the zip
 * file. If not, see <a
 * href="http://www.gnu.org/licenses/">www.gnu.org/licenses</a>.
 * </p>
 * 
 * <p>
 * For more details on SimpleNLG visit the project website at <a
 * href="http://www.csd.abdn.ac.uk/research/simplenlg/"
 * >www.csd.abdn.ac.uk/research/simplenlg</a> or email Dr Ehud Reiter at
 * e.reiter@abdn.ac.uk
 * </p>
 * 
 * @author ereiter
 * 
 */
public class TutorialTest extends SimpleNLG4Test {

	public TutorialTest(String name) {
		super(name);
	}


	// no code in sections 1 and 2
	
	/**
	 * test section 3 code
	 */
	@Test
	public void testSection3() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();                         // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon

		NLGElement s1 = nlgFactory.createSentence("my dog is happy");
		
		Realiser r = new Realiser(lexicon);
		
		String output = r.realiseSentence(s1);
		
		Assert.assertEquals("My dog is happy.", output);
	 }
	
	/**
	 * test section 5 code
	 */
	@Test
	public void testSection5() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();                         // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		
		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("my dog");
		p.setVerb("chase");
		p.setObject("George");
		
		String output = realiser.realiseSentence(p);
		Assert.assertEquals("My dog chases George.", output);
	 }
	
	/**
	 * test section 6 code
	 */
	@Test
	public void testSection6() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();                         // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		
		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("Mary");
		p.setVerb("chase");
		p.setObject("George");
		
		p.setFeature(Feature.TENSE, Tense.PAST); 
		String output = realiser.realiseSentence(p);
		Assert.assertEquals("Mary chased George.", output);

		p.setFeature(Feature.TENSE, Tense.FUTURE); 
		output = realiser.realiseSentence(p);
		Assert.assertEquals("Mary will chase George.", output);

		p.setFeature(Feature.NEGATED, true); 
		output = realiser.realiseSentence(p);
		Assert.assertEquals("Mary will not chase George.", output);

		p = nlgFactory.createClause();
		p.setSubject("Mary");
		p.setVerb("chase");
		p.setObject("George");
 
		p.setFeature(Feature.INTERROGATIVE_TYPE,
				InterrogativeType.YES_NO);
		output = realiser.realiseSentence(p);
		Assert.assertEquals("Does Mary chase George?", output);

		p.setSubject("Mary");
		p.setVerb("chase");
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_OBJECT);
		output = realiser.realiseSentence(p);
		Assert.assertEquals("Who does Mary chase?", output);

		p = nlgFactory.createClause();
		p.setSubject("the dog");
		p.setVerb("wake up");
		output = realiser.realiseSentence(p);
		Assert.assertEquals("The dog wakes up.", output);

	 }
	
	/**
	 * test ability to use variant words
	 */
	@Test
	public void testVariants() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();                         // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		
		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("my dog");
		p.setVerb("is");  // variant of be
		p.setObject("George");
		
		String output = realiser.realiseSentence(p);
		Assert.assertEquals("My dog is George.", output);
		
		p = nlgFactory.createClause();
		p.setSubject("my dog");
		p.setVerb("chases");  // variant of chase
		p.setObject("George");
		
		output = realiser.realiseSentence(p);
		Assert.assertEquals("My dog chases George.", output);
		

        p = nlgFactory.createClause();
		p.setSubject(nlgFactory.createNounPhrase("the", "dogs"));   // variant of "dog"
		p.setVerb("is");  // variant of be
		p.setObject("happy");  // variant of happy
		output = realiser.realiseSentence(p);
		Assert.assertEquals("The dog is happy.", output);
		
		p = nlgFactory.createClause();
		p.setSubject(nlgFactory.createNounPhrase("the", "children"));   // variant of "child"
		p.setVerb("is");  // variant of be
		p.setObject("happy");  // variant of happy
		output = realiser.realiseSentence(p);
		Assert.assertEquals("The child is happy.", output);

		// following functionality is enabled
		p = nlgFactory.createClause();
		p.setSubject(nlgFactory.createNounPhrase("the", "dogs"));   // variant of "dog"
		p.setVerb("is");  // variant of be
		p.setObject("happy");  // variant of happy
		output = realiser.realiseSentence(p);
		Assert.assertEquals("The dog is happy.", output); //corrected automatically		
	}
		
	/* Following code tests the section 5 to 15
	 * sections 5 & 6 are repeated here in order to match the simplenlg tutorial version 4
	 * James Christie
	 * June 2011
	 */

	/**
	 * test section 5 to match simplenlg tutorial version 4's code
	 */
	@Test
		public void testSection5A( ) { 
			Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;      // default simplenlg lexicon
			NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon
			Realiser realiser = new Realiser( lexicon ) ;
			
			SPhraseSpec p = nlgFactory.createClause( ) ;
			p.setSubject( "Mary" ) ;
			p.setVerb( "chase" ) ;
			p.setObject( "the monkey" ) ;
			
			String output = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Mary chases the monkey.", output ) ;
		 } // testSection5A
	
	/**
	 * test section 6 to match simplenlg tutorial version 4' code
	 */
	@Test
	public void testSection6A( ) { 
		Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;    // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon
		Realiser realiser = new Realiser( lexicon ) ;
	
		SPhraseSpec p = nlgFactory.createClause( ) ;
		p.setSubject( "Mary" ) ;
		p.setVerb( "chase" ) ;
		p.setObject( "the monkey" ) ;
	
		p.setFeature( Feature.TENSE, Tense.PAST ) ; 
		String output = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "Mary chased the monkey.", output ) ;

		p.setFeature( Feature.TENSE, Tense.FUTURE ) ; 
		output = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "Mary will chase the monkey.", output ) ;

		p.setFeature( Feature.NEGATED, true ) ; 
		output = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "Mary will not chase the monkey.", output ) ;

		p = nlgFactory.createClause( ) ;
		p.setSubject( "Mary" ) ;
		p.setVerb( "chase" ) ;
		p.setObject( "the monkey" ) ;

		p.setFeature( Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO ) ;
		output = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "Does Mary chase the monkey?", output ) ;

		p.setSubject( "Mary" ) ;
		p.setVerb( "chase" ) ;
		p.setFeature( Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_OBJECT ) ;
		output = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "Who does Mary chase?", output ) ;
	} // textSection6A
	
	/**
	 * test section 7 code
	 */
	@Test
		public void testSection7( ) { 
			Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;      // default simplenlg lexicon
			NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon
			Realiser realiser = new Realiser( lexicon ) ;
			
			SPhraseSpec p = nlgFactory.createClause( ) ;
			p.setSubject( "Mary" ) ;
			p.setVerb( "chase" ) ;
			p.setObject( "the monkey" ) ;
			p.addComplement( "very quickly" ) ;
			p.addComplement( "despite her exhaustion" ) ;
			
			String output = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Mary chases the monkey very quickly despite her exhaustion.", output ) ;
		 } // testSection7
	
	/**
	 * test section 8 code
	 */
	@Test
		public void testSection8( ) { 
			Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;      // default simplenlg lexicon
			NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon
			Realiser realiser = new Realiser( lexicon ) ;
			
			NPPhraseSpec subject = nlgFactory.createNounPhrase( "Mary" ) ;
			NPPhraseSpec object = nlgFactory.createNounPhrase( "the monkey" ) ;
			VPPhraseSpec verb = nlgFactory.createVerbPhrase( "chase" ) ; ;
			subject.addModifier( "fast" ) ;
			
			SPhraseSpec p = nlgFactory.createClause( ) ;
			p.setSubject( subject ) ;
			p.setVerb( verb ) ;
			p.setObject( object ) ;
			
			String outputA = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Fast Mary chases the monkey.", outputA ) ;
			
			verb.addModifier( "quickly" ) ;
			
			String outputB = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Fast Mary quickly chases the monkey.", outputB ) ;
		 } // testSection8
	
	// there is no code specified in section 9
	
	/**
	 * test section 10 code
	 */
	@Test
		public void testSection10( ) { 
			Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;      // default simplenlg lexicon
			NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon
			Realiser realiser = new Realiser( lexicon ) ;
			
			NPPhraseSpec subject1 = nlgFactory.createNounPhrase( "Mary" ) ;
			NPPhraseSpec subject2 = nlgFactory.createNounPhrase( "your", "giraffe" ) ;
			
			// next line is not correct ~ should be nlgFactory.createCoordinatedPhrase ~ may be corrected in the API
			CoordinatedPhraseElement subj = nlgFactory.createCoordinatedPhrase( subject1, subject2 ) ;
			
			VPPhraseSpec verb = nlgFactory.createVerbPhrase( "chase" ) ; ;

			SPhraseSpec p = nlgFactory.createClause( ) ;
			p.setSubject( subj ) ;
			p.setVerb( verb ) ;
			p.setObject( "the monkey" ) ;
			
			String outputA = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Mary and your giraffe chase the monkey.", outputA ) ;
			
			NPPhraseSpec object1 = nlgFactory.createNounPhrase( "the monkey" ) ;
			NPPhraseSpec object2 = nlgFactory.createNounPhrase( "George" ) ;
			
			// next line is not correct ~ should be nlgFactory.createCoordinatedPhrase ~ may be corrected in the API
			CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase( object1, object2 ) ;
			obj.addCoordinate( "Martha" ) ;
			p.setObject( obj ) ;
			
			String outputB = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Mary and your giraffe chase the monkey, George and Martha.", outputB ) ;	

			obj.setFeature( Feature.CONJUNCTION, "or" ) ;
			
			String outputC = realiser.realiseSentence( p ) ;
			Assert.assertEquals( "Mary and your giraffe chase the monkey, George or Martha.", outputC ) ;	
	} // testSection10
	
	/**
	 * test section 11 code
	 */
	@SuppressWarnings({ "deprecation" })
	@Test
	public void testSection11( ) {
		Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;     // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon

		Realiser realiser = new Realiser( lexicon ) ;
		
		SPhraseSpec pA = nlgFactory.createClause( "Mary", "chase", "the monkey" ) ;
		pA.addComplement( "in the park" ) ;
		
		String outputA = realiser.realiseSentence( pA ) ;		
		Assert.assertEquals( "Mary chases the monkey in the park.", outputA ) ;
		
		// alternative build paradigm
		NPPhraseSpec place = nlgFactory.createNounPhrase( "park" ) ;
		SPhraseSpec pB = nlgFactory.createClause( "Mary", "chase", "the monkey" ) ;
		
		// next line is depreciated ~ may be corrected in the API
		place.setDeterminer( "the" ) ;
		PPPhraseSpec pp = nlgFactory.createPrepositionPhrase( ) ;
		pp.addComplement( place ) ;
		pp.setPreposition( "in" ) ;
		
		pB.addComplement( pp ) ;
		
		String outputB = realiser.realiseSentence( pB ) ;		
		Assert.assertEquals( "Mary chases the monkey in park.", outputB ) ;	// NB missing the determiner "the" !!	
		
		place.addPreModifier( "leafy" ) ;
		
		String outputC = realiser.realiseSentence( pB ) ;		
		Assert.assertEquals( "Mary chases the monkey in leafy park.", outputC ) ;	// NB missing the determiner "the" !!	
	 } // testSection11

	// section12 only has a code table as illustration
	
	/**
	 * test section 13 code
	 */
	@Test
	public void testSection13( ) {
		Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;     // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon

		Realiser realiser = new Realiser( lexicon ) ;
	
		SPhraseSpec s1 = nlgFactory.createClause( "my cat",   "like", "fish"  ) ;
		SPhraseSpec s2 = nlgFactory.createClause( "my dog",  "like",  "big bones" ) ;
		SPhraseSpec s3 = nlgFactory.createClause( "my horse", "like", "grass" ) ;		
		
		CoordinatedPhraseElement c = nlgFactory.createCoordinatedPhrase( ) ;
		c.addCoordinate( s1 ) ;
		c.addCoordinate( s2 ) ; // gives the wrong result ~ should be 'bones' but get 'bone' !
		c.addCoordinate( s3 ) ;
		
		String outputA = realiser.realiseSentence( c ) ;
		Assert.assertEquals( "My cat likes fish, my dog likes big bones and my horse likes grass.", outputA ) ;
		
		SPhraseSpec p = nlgFactory.createClause( "I", "be",  "happy" ) ;
		SPhraseSpec q = nlgFactory.createClause( "I", "eat", "fish" ) ;
		q.setFeature( Feature.COMPLEMENTISER, "because" ) ;
		q.setFeature( Feature.TENSE, Tense.PAST ) ;
		p.addComplement( q ) ;
		
		String outputB = realiser.realiseSentence( p ) ;
		Assert.assertEquals( "I am happy because I ate fish.", outputB ) ;
	} // testSection13
	
	/**
	 * test section 14 code
	 */
	@Test
	public void testSection14( ) {
		Lexicon lexicon = Lexicon.getDefaultLexicon( ) ;     // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory( lexicon ) ;  // factory based on lexicon

		Realiser realiser = new Realiser( lexicon ) ;
	
		SPhraseSpec p1 = nlgFactory.createClause( "Mary", "chase", "the monkey" ) ;
		SPhraseSpec p2 = nlgFactory.createClause( "The monkey", "fight back" ) ;
		SPhraseSpec p3 = nlgFactory.createClause( "Mary", "be", "nervous" ) ;
		
		DocumentElement s1 = nlgFactory.createSentence( p1 ) ;
		DocumentElement s2 = nlgFactory.createSentence( p2 ) ;
		DocumentElement s3 = nlgFactory.createSentence( p3 ) ;
		
		DocumentElement par1 = nlgFactory.createParagraph( Arrays.asList( s1, s2, s3 ) ) ;
		
		String output14a = realiser.realise( par1 ).getRealisation() ;
		Assert.assertEquals( "Mary chases the monkey. The monkey fights back. Mary is nervous.\n\n", output14a ) ;
		//   actual output ~  Mary chases the monkey. The monkey fights back. Mary is nervous.
		// So what exactly is JUnit not happy about?
 
		DocumentElement section = nlgFactory.createSection( "The Trials and Tribulation of Mary and the Monkey" ) ;
        section.addComponent( par1 ) ;
        String output14b = realiser.realise( section ).getRealisation() ;
        Assert.assertEquals( "The Trials and Tribulation of Mary and the Monkey\nMary chases the monkey. The monkey fights back. Mary is nervous.\n\n", output14b ) ;
	} // testSection14

} // class
