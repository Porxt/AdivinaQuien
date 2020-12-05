% Base de conocimiento enfocado en las caracteristicas
caracteristica_de(
    hombre,
    [spiderman, ironman, 'capitan america', hulk, loki, vision, wolverine, magneto, 'profesor x', bestia, quicksilver]
).
caracteristica_de(
    mujer,
    ['bruja escarlata', 'viuda negra', 'carol danvers', gamora, 'jean grey', mystique, rogue, tormenta]
).

caracteristica_de(
    'piel clara',
    [spiderman, ironman, 'capitan america', 'bruja escarlata', hulk, loki, 'viuda negra', 'carol danvers', wolverine, 'jean grey', rogue, magneto, 'profesor x', quicksilver]
).
caracteristica_de('piel roja', [vision]).
caracteristica_de('piel verde', [gamora]).
caracteristica_de('piel azul', [mystique, bestia]).
caracteristica_de('piel morena', [tormenta]).

caracteristica_de(
    'con cabello',
    [spiderman, 'capitan america', 'bruja escarlata', hulk, loki, 'viuda negra', 'carol danvers', gamora, wolverine, 'jean grey', mystique, rogue, magneto, bestia, quicksilver, tormenta]
).
caracteristica_de('sin cabello', [vision, 'profesor x']).

caracteristica_de('cabello cafe', [spiderman, ironman, 'capitan america', magneto]).
caracteristica_de('cabello rubio', ['bruja escarlata', 'carol danvers']).
caracteristica_de('cabello negro', [hulk, loki, wolverine, rogue]).
caracteristica_de('cabello rojo', ['viuda negra', gamora, 'jean grey', mystique]).
caracteristica_de('cabello blanco', [quicksilver, tormenta]).
caracteristica_de('cabello azul', [bestia]).

caracteristica_de('cabello ondulado', ['bruja escarlata', hulk, 'jean grey']).
caracteristica_de(
    'cabello lacio',
    [spiderman, ironman, 'capitan america', loki, 'viuda negra', 'carol danvers', gamora, wolverine, mystique, rogue, magneto, bestia, quicksilver, tormenta]
).

caracteristica_de(
    'cabello corto',
    [spiderman, ironman, 'capitan america', hulk, wolverine, mystique, magneto, bestia, quicksilver, tormenta]
).
caracteristica_de('cabello medio', ['viuda negra', 'carol danvers', gamora, 'jean grey', rogue]).
caracteristica_de('cabello largo', ['bruja escarlata', loki]).

caracteristica_de(
    'ojos cafes',
    [spiderman, ironman, hulk, 'carol danvers', gamora, wolverine, rogue, quicksilver, tormenta]
).
caracteristica_de(
    'ojos azules',
    ['capitan america', 'bruja escarlata', loki, vision, 'jean grey', magneto, 'profesor x']
).
caracteristica_de('ojos amarillos', [mystique, bestia]).
caracteristica_de('ojos verdes', ['viuda negra']).

% Pregunta True/False donde:
% C - Es la caracteristica
% P - Es el personaje correcto
% X - Es la lista de personajes que tienen C
pregunta(C, P):- caracteristica_de(C, X), member(P, X).
