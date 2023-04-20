export interface Card { 
    nameShort?: string;
    name?: string;
    value?: string;
    valueInt?: number;
    type?: Card.TypeEnum;
    meaningUp?: string;
    meaningRev?: string;
    desc?: string;
}
export namespace Card {
    export type TypeEnum = 'major' | 'minor';
    export const TypeEnum = {
        Major: 'major' as TypeEnum,
        Minor: 'minor' as TypeEnum
    };
}