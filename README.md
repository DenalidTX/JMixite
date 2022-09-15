# JMixite
This is a pure Java port of the Mixite library by Hexworks. The original Mixite library is fully functional, but it did not integrate well with Java 9+ in all cases. This library has the same functionality, with some design differences. These changes are not proposed as an update to Mixite itself, however, because these design differences alter the interfaces and fundamental organization of information.

_Aside: The original mixite library is fully functional and fit for purpose; this project exists primarily because the Kotlin library did not integrate well with Java 16+ JPMS projects. The changes described below reflect my own code style and design preferences._

## Design Principles
- The external interfaces should be intuitive and simple, requiring minimal boilerplate code.
- Enforce the Open/Closed Principle.
- Provide the simplest code possible while maintaining performance.

## Design Updates
The key changes that differentiate this library from the original mixite involve the organization of data related to individual hexagons:
- Hexagon is now a shape, nothing more. The functions beyond geometry now reside in GridCell.
- GridCell (unlike the old Hexagon) is persistent and specific to a grid, rather than transient during some calculations.
- This library does not provide a SatelliteData base class; it now considers the GridCell content to be fully implementation-specific.
- There is no separate storage class. My use cases don't require the small performance gains that custom storage provides, so the added complexity is not worthwhile for me.
