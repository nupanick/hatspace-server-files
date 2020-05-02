__config -> m(
	l('stay_loaded', true),
	l('scope', 'global')
)

__on_tick() -> tick();
__on_tick_nether() -> tick();
__on_tick_ender() -> tick();

below(pos) -> l(pos:0, pos:1 - 1, pos:2);

become_tiny_potato(e) -> (
	count = e~'item':1;
	s = spawn('item', e~'pos', '{Item:{
		id:"player_head",
		Count:1,
		tag:{display:{Name:"{\\"text\\":\\"Tiny Potato\\"}"},SkullOwner:{Id:"9da44549-cde5-4748-8341-2a3899087a9f",Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNjYzc5ZmU5OWY0MzMxMDFkOTJmMzQ1ZmIyMjM5ZGIzMGQwNzM5Y2NjYTkzMzlmYTI2MjNlYjZiMDZhNTgzYSJ9fX0="}]}}}
	}}');
	modify(e,'remove');
);	

tick() -> for(
	filter(
		entity_list('items'),
		_~'item':0 == 'potato' &&
		block(_~'pos') == 'cauldron' &&
		property(_~'pos','level') > 0 &&
		block(below(_~'pos')) == 'fire'
		// TODO: make this only work for soul fire.
	),
	become_tiny_potato(_)
);
