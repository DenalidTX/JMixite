/**
 * Right now everything is exported. That might seem to negate the
 * module packaging, but by explicitly defining the module we help
 * users of this library ensure smooth module integration. It is a
 * sad but true fact that automatic modules fail alarmingly often.
 */

module jmixite.core
{
    exports org.hexworks.jmixite.core.geometry;
    exports org.hexworks.jmixite.core.grid;
    exports org.hexworks.jmixite.core.grid.builder;
    exports org.hexworks.jmixite.core.grid.layout;
    exports org.hexworks.jmixite.core.grid.calculator;
}