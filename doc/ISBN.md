#  The ISBN format

I have not yet read the *ISBN Users' Manual* from the International ISBN Agency, 
though I do have the Sixth Edition as a PDF and will list it in 
[the bibliography](bibliography.bib).

The International Standard Book Number (ISBN) format allows each book published 
anywhere in the world to be uniquely identified by a 10- or 13-digit number. 
These numbers consist of four or five elements, which are typically separated by 
dashes or spaces in the human-readable number printed on the book itself, but 
those separators are omitted from the barcode.

ISBNs are administered by the International ISBN Agency, which has affiliate 
agencies in several countries. Publishers buy ISBNs from the agencies in the 
countries where they publish.

A publisher can buy an ISBN in advance of the proofreading process for a book 
being completed, or the book even being written. Buying ISBNs in advance 
certainly makes sense for large publishers. Before the books become available to 
the general public, the publisher registers the titles with the affiliate agency 
they purchased the ISBNs from.

Keep in mind that many titles have multiple ISBNs. This is especially the case 
with famous public domain titles, such as *The Hound of the Baskervilles* by Sir 
Arthur Conan Doyle in English, which has some three or four thousand ISBNs, and 
that's ignoring scripts for theatrical adaptations and translations to other 
languages.

## ISBN-10

Although ISBN-10 is deprecated in favor of ISBN-13, the large amount of books 
printed with the old numbers that are still in libraries everywhere prevents the 
complete abandonment of ISBN-10 numbers. The old ISBN-10 numbers can be 
converted to ISBN-13 numbers, as detailed later on in this document.

The expansion was necessary because ISBN-10 couldn't accommodate more than a 
billion books. Some of the examples in this document are the ISBNs of real books 
that I have read. But given the scarcity of unassigned ISBN-10 numbers, it is 
entirely possible that some of the other examples I chose also correspond to 
real books, just not books that I have read or would have ever come across 
otherwise. Those might be books that are not even available to me.

An ISBN-10 number consists of four elements, which are also present in ISBN-13:

* The registration group element, which consists of one to five digits and 
identifies the registration group the publisher is in. For example, the 
registration group element for many books in English printed in the United 
States are in registration group 0.
* The registrant element, which may consist of up to seven digits depending on 
how many digits are taken up by the registration group element, and identifies 
the publisher as registered for ISBN. For example, Riverhead Books, an imprint 
of Penguin Random House LLC which is in registration group 0, has registrant 
number 7352.
* The publication element, which may consist of up to six digits depending on 
how many digits are taken up by the previous two elements, identifies the book 
itself. The International ISBN Agency expects publishers to use separate ISBNs 
for different formats of the same book. For example, the original hardcover 
edition of *Kingdom of Characters* by Jing Tsu was publication 1474 for 
registrant Riverhead Books. When the book was nominated as a finalist for the 
Pulitzer Prize, it was reprinted as a trade paperback as publication 1473 for 
registrant 7352. It is entirely up to the publisher to determine how to allocate 
the numbers they have purchased.
* The check digit is the final digit, it may be any of 0 to 10, that last one 
written as 'X' when it comes up. The check digit is calculated according to a 
mathematical formula on the previous nine digits. The check digit reassures us 
that a barcode has been scanned correctly, or that the number has been typed 
correctly when the barcode is not scannable.

The formula for the check digit *could* have been a simple checksum. For 
example, add up the nine digits for which the check digit is needed, then take 
the final digit of that sum. In the case of 123546789, the digits add up to 45, 
so in this simple scheme the check digit would be 5.

Such a formula would fail to catch errors in which digits are transposed. With 
that formula, 123456789 would also have check digit 5.

The actual ISBN-10 check digit formula does sum up the digits, but the digits 
are weighted: the second digit is doubled, the third digit is tripled, and so on 
and so forth up to the ninth digit being multiplied by 9.

If this weighted sum is a multiple of 11, then the check digit is 0. Otherwise, 
reckon the remainder of the weighted sum divided by 11. If the check digit is 
10, then it is written as 'X'.

In the examples, 123546789 got check digit 9, while 123456789 got check digit X, 
since $1 + 2^2 + 3^2 + 5 \times 4 + 4 \times 5 + 6^2 + 7^2 + 8^2 + 9^2 = 284$ 
and $284 \equiv 9 \pmod{11}$, whereas $$\sum_{i = 1}^9 d_i i^2 = 285$$ (where
$d_1$ is the most significant digit and $d_9$ is the digit before the check 
digit) and obviously $285 \equiv 10 \pmod{11}$.

## ISBN-13

The newer format was instituted in 2007, well before publishers started running 
out of ISBN-10 numbers. It was devised in such a way that any book with an 
ISBN-10 number automatically has an ISBN-13 number as well.

An ISBN-13 number consists of five elements, four of which are also applicable 
to ISBN-10:

* A prefix element of three digits decreed by the International ISBN Agency. At 
first, this was always 978, and that will always be the prefix when converting 
ISBN-10 numbers to ISBN-13. Many newer books have also been assigned prefix 978, 
which means that technically those books also have ISBN-10 numbers. A few years 
ago, the agency started assigning prefix 979. Those numbers are not backwards 
compatible to ISBN-10.
* The registration group element. All the registration groups used for ISBN-10 
remain valid for ISBN-13 with the 978 prefix. Just as with ISBN-10, this element 
can vary in length from one digit to five digits.
* The registrant element. These are probably different between the 978 and 979 
prefixes because of some publishers going out of business and new ones coming 
in, established houses reorganizing their imprints, etc. The number of digits 
for this element is subject to the same constraints as in ISBN-10.
* The publication element, subject to the same constraints as in ISBN-10.
* The check digit is the final digit, it may be any of 0 to 9. It is calculated 
according to a mathematical formula on the previous twelve digits. But it is 
a different formula from the one used for ISBN-10.

The formula for check digits in ISBN-13 also involves a weighted sum of the 
previous digits, but the multiplier for the weight alternate between 1 and 3 
instead of increasing like in the ISBN-10 formula. If this weighted sum is a 
multiple of 10, then the check digit is 0. Otherwise, the weighted sum is 
multiplied by &minus;1 and the check digit is the remainder modulo 10.

The 978 prefix contributes 38 to the weighted sum, whereas the 979 prefix 
contributes 39.

For example, for 978-012345678, we have 
$38 + 3 \times (2 + 4 + 6 + 8) + (1 + 3 + 5 + 7) = 114$. Then we see that 
$-114 \equiv 6 \pmod {10}$, so the check digit is 6. If the twelve digits were 
instead 978-012354678, the calculation would be 
$38 + 3 \times (2 + 5 + 6 + 8) + (1 + 3 + 4 + 7) = 116$ followed by 
$-116 \equiv 4 \pmod {10}$, so the check digit is 4.

### Converting ISBN-10 to ISBN-13

To convert an ISBN-10 number to ISBN-13, place the prefix 978 in front of the 
ISBN-10 number, discard the original check digit and recalculate it according to 
the formula for ISBN-13. The element separators ought to remain the same in the 
human-readable form, and a new separator is added between the 978 prefix and the 
registrant group element.

Recall the earlier example 123456789-X. Though this example corresponds to an 
actual book, it's not one that I have actually read and I will not trouble 
myself to figure out all the element separators, so I'm only putting in the ones 
that I know for certain. Adding the prefix 978 and dropping the original check 
digit X, we recalculate the check digit and thus obtain 978-123456789-7.

Sometimes it happens that the check digit remains the same, though clearly this 
can't happen when the ISBN-10 check digit is X. For example, *Calculus Made 
Easy* by Sylvanus P. Thompson and Martin Gardner was published as a hardcover in 
1998 with ISBN 0-312-18548-0. Converted to ISBN-13, its check digit is also 0, 
and so we have 978-0-312-18548-0.

### Converting ISBN-13 to ISBN-10

Generally, converting ISBN-13 to ISBN-10 should only be done for the sake of 
older software that can't handle ISBN-13. Those programs will need to either be 
upgraded or replaced, as they won't be able to cope with the 979 prefix and 
potentially the 980 prefix or other prefixes the International ISBN Agency might 
issue.

To convert an ISBN-13 number with prefix 978 to ISBN-10, delete the 978 prefix, 
discard the check digit and recalculate it according to the formula for ISBN-10. 
For example, the paperback reprint of *Kingdom of Characters* by Jing Tsu has 
ISBN 978-0-7352-1473-6. Deleting the 978 prefix and discarding check digit 6, we 
reckon the ISBN-10 check digit for 0-7352-1473, which is 5.

A number of workarounds suggest themselves for dealing with the 979 prefix, but 
they're all tantamount to kicking the can down the road. Therefore, it is much 
preferable to upgrade old programs to handle ISBN-13 correctly.
