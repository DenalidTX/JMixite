# mixite_2
This is a redesign of the mixite library by Hexworks. The original mixite library is fully functional, but it wasn't intuitive for all developers. This library has the same functionality, but with some key design differences. These changes are not proposed as an update to mixite itself, however, because these design differences alter the interfaces and fundamental organization of information.

_Aside: The original mixite library is fully functional and fit for purpose. This project exists simply because my own code style favors a different organizational scheme._

## Design Principles
- The external interfaces should be intuitive and simple, requiring minimal boilerplate code.
- _Strongly_ enforce the Open/Closed Principle.
- Provide the simplest code possible while maintaining performance.

## Design Updates
The key changes that differentiate this library from the original mixite involve the organization of data related to individual hexagons:
- Hexagon is now a shape, nothing more. The functions beyond geometry now reside in GridCell.
- GridCell (unlike the old Hexagon) is persistent and specific to a grid, rather than transient during some calculations.
- This library does not provide a SatelliteData base class; it now considers the GridCell content to be fully implementation-specific.
